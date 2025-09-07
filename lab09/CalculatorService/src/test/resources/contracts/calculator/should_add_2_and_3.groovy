package contracts.calculator

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "2 + 3 = 5"
    request {
        method GET()
        url("/add") {
            queryParameters {
                parameter("value1", '2')
                parameter("value2", '3')
            }
        }
    }
    response {
        status 200
        body("5")
        headers { contentType(textPlain()) }
    }
}
