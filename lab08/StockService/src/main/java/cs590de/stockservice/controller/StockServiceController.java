package cs590de.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockServiceController {
    private final Map<String, Integer> db = Map.of(
            "P100", 50,
            "P200", 0,
            "P300", 7
    );

    @GetMapping("/{productNumber}")
    public Integer get(@PathVariable String productNumber) {
        return db.getOrDefault(productNumber, 0);
    }
}
