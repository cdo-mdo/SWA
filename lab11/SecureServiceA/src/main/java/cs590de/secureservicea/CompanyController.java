package cs590de.secureservicea;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    private final RestClient rest;

    public CompanyController(RestClient rest) {
        this.rest = rest;
    }

    // ---- products: all roles ----
    @GetMapping("/products")
    public List<Map<String, Object>> products() {
        return List.of(
                Map.of("sku","P100","name","Widget","price",19.99),
                Map.of("sku","P200","name","Gadget","price",29.99)
        );
    }

    // ---- employee contacts: employees + managers, call B ----
    @GetMapping("/employee-contacts")
    public Map<String,String> employeeContacts() {
        String token = currentAccessToken();
        return this.rest.get()
                .uri("http://localhost:9091/employee-contacts")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // token relay
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    // ---- salaries: managers, call C ----
    @GetMapping("/salaries")
    public Map<String,Integer> salaries() {
        String token = currentAccessToken();
        return this.rest.get()
                .uri("http://localhost:9092/salaries")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    private String currentAccessToken() {
        var auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return auth.getToken().getTokenValue();
    }
}
