package org.example.tp12.metier;

import jakarta.transaction.Transactional;
import org.example.tp12.Dto.CompteDto;
import org.example.tp12.dao.ClientRepository;
import org.example.tp12.dao.CompteRepository;
import org.example.tp12.dao.EmployeRepository;
import org.example.tp12.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteMetierImpl implements CompteMetier {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeRepository employeRepository;


    public CompteDto addCompte(CompteDto compteDTO) {
        Compte compte ;


        if ("CE".equals(compteDTO.getType())) {
            compte = new CompteEpargne(compteDTO.getCodeCompte(), compteDTO.getDateCreation(), compteDTO.getSolde(), compteDTO.getTaux());
        } else if ("CC".equals(compteDTO.getType())) {
            compte = new CompteCourant(compteDTO.getCodeCompte(), compteDTO.getDateCreation(), compteDTO.getSolde(), compteDTO.getDecouvert());
        } else {
            throw new IllegalArgumentException("Invalid account type: " + compteDTO.getType());
        }


        Client client = clientRepository.findById(compteDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        Employe employe = employeRepository.findById(compteDTO.getEmployeId()).orElseThrow(() -> new RuntimeException("Employee not found"));

        compte.setClient(client);
        compte.setEmploye(employe);

        Compte savedCompte = compteRepository.save(compte);
        return mapToDTO(savedCompte);
    }


    public List<CompteDto> listAllComptes() {
        return compteRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public CompteDto findCompteByCode(Long codeCompte) {
        Compte compte = compteRepository.findById(codeCompte).orElseThrow(() -> new RuntimeException("Compte not found"));
        return mapToDTO(compte);
    }

    public CompteDto updateCompte(Long codeCompte, CompteDto compteDTO) {
        Compte compte = compteRepository.findById(codeCompte)
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        if ("CE".equals(compteDTO.getType())) {
            ((CompteEpargne) compte).setTaux(compteDTO.getTaux());
        } else if ("CC".equals(compteDTO.getType())) {
            ((CompteCourant) compte).setDecouvert(compteDTO.getDecouvert());
        }

        compte.setSolde(compteDTO.getSolde());
        compte.setDateCreation(compteDTO.getDateCreation());

        Client client = clientRepository.findById(compteDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Employe employe = employeRepository.findById(compteDTO.getEmployeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        compte.setClient(client);
        compte.setEmploye(employe);

        Compte updatedCompte = compteRepository.save(compte);
        return mapToDTO(updatedCompte);
    }

    public void deleteCompte(Long codeCompte) {
        Compte compte = compteRepository.findById(codeCompte)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        compteRepository.delete(compte);
    }
    public List<CompteDto> findAllComptesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return compteRepository.findByClient(client)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Long getCompteCount(){
        return compteRepository.count();
    }
    private CompteDto mapToDTO(Compte compte) {
        CompteDto dto = new CompteDto();
        dto.setCodeCompte(compte.getCodeCompte());
        dto.setDateCreation(compte.getDateCreation());
        dto.setSolde(compte.getSolde());
        dto.setClientId(compte.getClient().getCodeClient());
        dto.setEmployeId(compte.getEmploye().getCodeEmploye());
        dto.setNomClient(compte.getClient().getNomClient());
        dto.setNomEmploye(compte.getEmploye().getNomEmploye());
        if (compte instanceof CompteEpargne) {
            dto.setType("CE");
            dto.setTaux(((CompteEpargne) compte).getTaux());
        } else if (compte instanceof CompteCourant) {
            dto.setType("CC");
            dto.setDecouvert(((CompteCourant) compte).getDecouvert());
        }
        return dto;
    }
}
