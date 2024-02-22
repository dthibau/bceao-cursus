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
            body(file("request.json"))
        }
    }
    response {
        status 201
        body([
                id: $(producer(regex('[0-99999]'))),
                nomBanque: 'string'
        ])
    }
}

