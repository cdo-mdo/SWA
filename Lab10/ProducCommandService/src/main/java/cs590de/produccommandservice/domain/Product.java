package cs590de.produccommandservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;

    @Indexed(unique = true)
    private String productNumber;

    private String name;
    private BigDecimal price;

    public Product() {

    }

    public Product(String productNumber, String name, BigDecimal price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

}
