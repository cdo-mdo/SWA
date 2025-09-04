package cs590de.stockcommandservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @Indexed(unique = true)
    private String productNumber;

    private String name;
    private BigDecimal price;

    public Product() { }

    public Product(String productNumber, String name, BigDecimal price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    // getters and setters
    public String getId() { return id; }
    public String getProductNumber() { return productNumber; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }

    public void setId(String id) { this.id = id; }
    public void setProductNumber(String productNumber) { this.productNumber = productNumber; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
