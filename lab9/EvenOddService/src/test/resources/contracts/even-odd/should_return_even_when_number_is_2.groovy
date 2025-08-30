import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "returns 'Even' when number=2"
    request {
        method GET()
        url("/validate") {
            queryParameters { parameter("number", '2') }
        }
    }
    response {
        status 200
        body("Even")
        headers { contentType(textPlain()) }
    }
}
