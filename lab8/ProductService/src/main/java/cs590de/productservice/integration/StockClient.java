package cs590de.productservice.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "stock-service", path="/api/stocks")
public interface StockClient {
    @GetMapping("{productNumber}")
    Integer getStock(String productNumber);
}
