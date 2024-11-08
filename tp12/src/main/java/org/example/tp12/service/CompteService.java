package org.example.tp12.service;

import org.example.tp12.Dto.CompteDto;
import org.example.tp12.entities.Compte;
import org.example.tp12.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compts")
public class CompteService {

    @Autowired
    private CompteMetier compteMetier;

    // Endpoint to add a new Compte
    @PostMapping
    public ResponseEntity<CompteDto> addCompte(@RequestBody CompteDto compteDto) {
        CompteDto savedCompte = compteMetier.addCompte(compteDto);
        return ResponseEntity.ok(savedCompte);
    }

    // Endpoint to list all Comptes
    @GetMapping
    public ResponseEntity<List<CompteDto>> listAllComptes() {
        List<CompteDto> comptes = compteMetier.listAllComptes();
        return ResponseEntity.ok(comptes);
    }
    // Endpoint to update an existing Compte
    @PutMapping("/{codeCompte}")
    public ResponseEntity<CompteDto> updateCompte(@PathVariable Long codeCompte, @RequestBody CompteDto compteDto) {
        CompteDto updatedCompte = compteMetier.updateCompte(codeCompte, compteDto);
        return ResponseEntity.ok(updatedCompte);
    }

    // Endpoint to delete a Compte by its code
    @DeleteMapping("/{codeCompte}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long codeCompte) {
        compteMetier.deleteCompte(codeCompte);
        return ResponseEntity.noContent().build();
    }
    // Endpoint to find a Compte by its code
    @GetMapping("/{codeCompte}")
    public ResponseEntity<CompteDto> findCompteByCode(@PathVariable Long codeCompte) {
        CompteDto compte = compteMetier.findCompteByCode(codeCompte);
        return ResponseEntity.ok(compte);
    }
}
