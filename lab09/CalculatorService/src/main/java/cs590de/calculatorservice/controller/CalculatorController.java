package cs590de.calculatorservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {
    @GetMapping("/add")
    public String add(@RequestParam("value1") Integer value1,
                      @RequestParam("value2") Integer value2) {
        return String.valueOf(value1 + value2);
    }
}
