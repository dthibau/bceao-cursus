package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method POST()
        headers {
            contentType(applicationJson())
        }
        url("/api/1.0/banques") {
            body([
                    nomBanque: $(consumer(regex('.*'))),
                    bic: "CMCIFR2A",
                    codeBanque: 12,
                    numero: 0,
                    rue: "string",
                    ville: "string",
                    codePostal: 0,
                    pays: "string"
            ])
        }
    }
    response {
        status 201
        body(
                id: $(producer(regex('[0-99999]'))),
                nomBanque: $(consumer(regex('.*'))) // Expression SpEL pour représenter un champ dynamique dans la réponse
        )
    }
}

