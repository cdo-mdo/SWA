package cs590de.calculatorservice.contract;

import cs590de.calculatorservice.CalculatorServiceApplication;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = CalculatorServiceApplication.class)
@AutoConfigureMockMvc
public abstract class ContractBase {
    @Autowired MockMvc mockMvc;
    @BeforeEach void setup() { RestAssuredMockMvc.mockMvc(mockMvc); }
}