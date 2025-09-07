package cs590de.productservice.integration;

import cs590de.productservice.dto.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service")
public interface StockClient {
    @GetMapping("/stocks/{productNumber}")
    StockResponse getStock(@PathVariable String productNumber);
}

