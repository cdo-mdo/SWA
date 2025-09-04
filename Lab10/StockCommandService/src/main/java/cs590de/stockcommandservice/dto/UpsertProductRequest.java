package cs590de.stockcommandservice.dto;

import java.math.BigDecimal;

public class UpsertProductRequest {
    public String productNumber;
    public String name;
    public BigDecimal price;
}
