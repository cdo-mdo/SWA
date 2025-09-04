package cs590de.productqueryservice.dto;

import java.math.BigDecimal;

public class ProductView {
    private String productNumber;
    private String name;
    private BigDecimal price;
    private int numberInStock;

    public ProductView() { }

    public ProductView(String productNumber, String name, BigDecimal price, int numberInStock) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.numberInStock = numberInStock;
    }

    // getters/setters
    public String getProductNumber() { return productNumber; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getNumberInStock() { return numberInStock; }

    public void setProductNumber(String productNumber) { this.productNumber = productNumber; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setNumberInStock(int numberInStock) { this.numberInStock = numberInStock; }
}
