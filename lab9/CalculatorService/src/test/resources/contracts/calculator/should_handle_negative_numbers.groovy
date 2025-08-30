package contracts.calculator

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "-1 + 1 = 0"
    request {
        method GET()
        url("/add") {
            queryParameters {
                parameter("value1", '-1')
                parameter("value2", '1')
            }
        }
    }
    response {
        status 200
        body("0")
        headers { contentType(textPlain()) }
    }
}
