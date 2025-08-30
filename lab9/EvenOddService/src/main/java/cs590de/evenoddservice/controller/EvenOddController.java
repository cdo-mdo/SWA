package cs590de.evenoddservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EvenOddController {
    @GetMapping("/validate")
    public String isNumberPrime(@RequestParam("number") Integer number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }
}
