package org.example.tp12.metier;

import org.example.tp12.Dto.CompteDto;
import org.example.tp12.entities.Client;
import org.example.tp12.entities.Compte;

import java.util.List;

public interface CompteMetier {

    public CompteDto addCompte(CompteDto compteDTO);
    public List<CompteDto> listAllComptes();
    public CompteDto findCompteByCode(Long codeCompte);
}
