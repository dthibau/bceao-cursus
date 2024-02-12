package org.formation.banquesservice.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "kafka")
@Component
@Validated
@Data
public class KafkaConfig {

    @NotEmpty
    public String domainTopic;

}
