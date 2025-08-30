import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "returns 'Odd' when number=1"
    request {
        method GET()
        url("/validate") { queryParameters { parameter("number", '1') } }
    }
    response {
        status 200
        body("Odd")
        headers { contentType(textPlain()) }
    }
}
