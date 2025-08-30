package cs590de.stockservice.controller;

import cs590de.stockservice.dto.StockResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stocks")
public class StockServiceController {
    @Value("${server.port}")
    private String port;

    private final Map<String, Integer> db = Map.of(
        "P100", 25,
        "P200", 0,
        "P300", 7
    );

    @GetMapping("/{productNumber}")
    public StockResponse get(@PathVariable String productNumber) {
        int qty = db.getOrDefault(productNumber, 0);
        return new StockResponse(productNumber, qty, "stock: " + port);
    }


}
