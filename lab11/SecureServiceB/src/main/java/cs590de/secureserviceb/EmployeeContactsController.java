package cs590de.secureserviceb;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employee-contacts")
public class EmployeeContactsController {

    // Only employees and managers
    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping
    public Map<String, String> contacts() {
        return Map.of(
                "e1001", "555-0101",
                "e1002", "555-0102"
        );
    }
}
