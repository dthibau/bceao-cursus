package org.formation.gateway.web;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class BanqueDto {

    private String id;
    @JsonAlias("nomBanque")
    private String name;
    private String status;
    private List<String> actions;
}
