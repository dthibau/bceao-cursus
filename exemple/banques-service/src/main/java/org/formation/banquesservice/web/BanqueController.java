package org.formation.banquesservice.web;

import jakarta.validation.Valid;
import org.formation.banquesservice.dto.BanqueDto;
import org.formation.banquesservice.dto.CreateBanqueDto;
import org.formation.banquesservice.service.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/banques")
public class BanqueController {

    @Autowired
    BanqueService banqueService;

    @GetMapping
    public List<BanqueDto> getBanques() {
        return banqueService.getBanques();
    }
    @GetMapping("/{id}")
    public BanqueDto getBanque(@PathVariable Long id) {
        return banqueService.getBanque(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BanqueDto createBanque(@RequestBody @Valid CreateBanqueDto dto) {
        return banqueService.createBanque(dto);// Call workflow service
    }
}
