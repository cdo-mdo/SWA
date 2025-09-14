package cs590de.secureservicec;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/salaries")
public class SalariesController {

    // Managers only
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public Map<String, Integer> salaries() {
        return Map.of(
                "e1001", 120000,
                "e1002", 135000
        );
    }
}

