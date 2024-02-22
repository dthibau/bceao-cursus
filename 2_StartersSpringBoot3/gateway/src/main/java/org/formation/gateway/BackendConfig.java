package org.formation.gateway;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "backend")
@Component
@Data
@Validated
public class BackendConfig {
    @URL
    private String banqueRootUri;
    @URL
    private String workflowRootUri;
}
