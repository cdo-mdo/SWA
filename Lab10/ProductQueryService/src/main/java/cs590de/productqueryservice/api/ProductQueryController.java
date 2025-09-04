package cs590de.productqueryservice.api;

import cs590de.productqueryservice.dto.ProductView;
import cs590de.productqueryservice.service.ProductQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final ProductQueryService svc;

    public ProductQueryController(ProductQueryService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<ProductView> all() {
        return svc.findAll();
    }

    @GetMapping("/{productNumber}")
    public Object one(@PathVariable String productNumber) {
        var pv = svc.findOne(productNumber);
        if (pv == null) {
            return Map.of("error", "Product not found");
        }
        return pv;
    }
}
