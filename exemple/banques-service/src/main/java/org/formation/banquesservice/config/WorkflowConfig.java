package org.formation.banquesservice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "workflow-service")
@Component
@Validated
@Data
public class WorkflowConfig {

    @URL
    public String url;

    @NotEmpty
    public String workflowId;
}
