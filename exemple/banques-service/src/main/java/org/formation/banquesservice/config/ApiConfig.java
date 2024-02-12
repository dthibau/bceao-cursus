package org.formation.banquesservice.config;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "api")
@Component
@Validated
@Data
public class ApiConfig {

    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "Version format is not valid")
    public String version;
    public String name;

    public String description;
}
