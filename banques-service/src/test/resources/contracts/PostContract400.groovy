package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return 400 when BIC is incorrect"
    request{
        method POST()
        headers {
            contentType(applicationJson())
        }
        url("/api/1.0/banques") {
            body(file("badRequest.json"))
        }
    }
    response {
        status 400
    }
}

