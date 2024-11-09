package org.example.tp12.metier;

import org.example.tp12.Dto.CompteDto;
import org.example.tp12.entities.Client;
import org.example.tp12.entities.Compte;

import java.util.List;

public interface CompteMetier {

    public CompteDto addCompte(CompteDto compteDTO);
    public List<CompteDto> listAllComptes();
    public CompteDto findCompteByCode(Long codeCompte);
    public CompteDto updateCompte(Long codeCompte, CompteDto compteDTO);
    public void deleteCompte(Long codeCompte);
    public List<CompteDto> findAllComptesByClient(Long clientId);
}
