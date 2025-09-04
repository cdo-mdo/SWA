package cs590de.productqueryservice.service;

import cs590de.productqueryservice.dto.ProductView;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ProductQueryService {

    private final MongoTemplate mongoTemplate;

    public ProductQueryService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<ProductView> findAll() {
        Aggregation agg = newAggregation(
                // join stocks on productNumber
                lookup("stocks", "productNumber", "productNumber", "stock"),
                // flatten but keep products without stock
                unwind("stock", true),
                // set numberInStock = stock.quantity or 0 if missing
                addFields().addField("numberInStock")
                        .withValue(ConditionalOperators.ifNull("$stock.quantity").then(0))
                        .build(),
                project("productNumber", "name", "price")
                        .and("numberInStock").as("numberInStock"),
                sort(Sort.by(Sort.Direction.ASC, "productNumber"))
        );
        return mongoTemplate.aggregate(agg, "products", ProductView.class).getMappedResults();
    }

    public ProductView findOne(String productNumber) {
        MatchOperation match = match(org.springframework.data.mongodb.core.query.Criteria.where("productNumber").is(productNumber));
        Aggregation agg = newAggregation(
                match,
                lookup("stocks", "productNumber", "productNumber", "stock"),
                unwind("stock", true),
                addFields().addField("numberInStock")
                        .withValue(ConditionalOperators.ifNull("$stock.quantity").then(0))
                        .build(),
                project("productNumber", "name", "price")
                        .and("numberInStock").as("numberInStock"),
                limit(1)
        );
        var results = mongoTemplate.aggregate(agg, "products", ProductView.class).getMappedResults();
        return results.isEmpty() ? null : results.get(0);
    }
}
