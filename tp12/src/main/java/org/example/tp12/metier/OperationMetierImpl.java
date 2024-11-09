package org.example.tp12.metier;

import org.example.tp12.Dto.ClientDTo;
import org.example.tp12.Dto.OperationDto;
import org.example.tp12.Dto.RetraitDto;
import org.example.tp12.Dto.VersmentDto;
import org.example.tp12.dao.*;
import org.example.tp12.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class OperationMetierImpl implements OperationMetier {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CompteRepository compteRepository;

     @Autowired
     private EmployeRepository employeRepository;

    @Override
    public VersmentDto versementToAmie(VersmentDto versmentDto) {
        Compte compteSource = compteRepository.findById(versmentDto.getCompteSource())
                .orElseThrow(() -> new RuntimeException("Compte source not found"));
        Compte compteDestination = compteRepository.findById(versmentDto.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte destination not found"));

        if (compteSource.getSolde() < versmentDto.getMontant()) {
            throw new RuntimeException("Insufficient balance in the source account");
        }


        compteSource.setSolde(compteSource.getSolde() - versmentDto.getMontant());
        compteDestination.setSolde(compteDestination.getSolde() + versmentDto.getMontant());

         Employe employe = new Employe();
         employe= employeRepository.findById(versmentDto.getEmployeId()).orElseThrow(() -> new RuntimeException("Employe not found"));

        Versment versement = new Versment(new Date(), versmentDto.getMontant(),compteDestination);
        Retrait retrait = new Retrait(new Date(), versmentDto.getMontant(),compteSource);
        versement.setCompte(compteSource);
        retrait.setCompte(compteDestination);
        versement.setEmploye(employe);
        retrait.setEmploye(employe);
        operationRepository.save(versement);
        operationRepository.save(retrait);



        VersmentDto dto = new VersmentDto();
        dto.setNumeroOperation(versement.getNumeroOperation());
        dto.setDateOperation(versement.getDateOperation());
        dto.setMontant(versement.getMontant());
        dto.setCompteId(versmentDto.getCompteId());
        dto.setCompteSource(versmentDto.getCompteSource());
        dto.setEmployeId(versmentDto.getEmployeId());
        return dto;
    }
    @Override
    public VersmentDto versement(VersmentDto versmentDto) {
        Compte compteSource = compteRepository.findById(versmentDto.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte source not found"));


        if (compteSource.getSolde() < versmentDto.getMontant()) {
            throw new RuntimeException("Insufficient balance in the source account");
        }


        compteSource.setSolde(compteSource.getSolde() +versmentDto.getMontant());


        Employe employe = new Employe();
        employe= employeRepository.findById(versmentDto.getEmployeId()).orElseThrow(() -> new RuntimeException("Employe not found"));

        Versment versement = new Versment(new Date(), versmentDto.getMontant());
        versement.setCompte(compteSource);
        versement.setEmploye(employe);
        operationRepository.save(versement);


        VersmentDto dto = new VersmentDto();
        dto.setNumeroOperation(versement.getNumeroOperation());
        dto.setDateOperation(versement.getDateOperation());
        dto.setMontant(versement.getMontant());
        dto.setCompteId(versmentDto.getCompteId());
        dto.setEmployeId(versmentDto.getEmployeId());
        return dto;
    }
    @Override
    public RetraitDto retrait(RetraitDto retraitDto) {
        Compte compte = compteRepository.findById(retraitDto.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        if (compte.getSolde() < retraitDto.getMontant()) {
            throw new RuntimeException("Insufficient balance");
        }


        compte.setSolde(compte.getSolde() - retraitDto.getMontant());


        Retrait retrait = new Retrait(new Date(), retraitDto.getMontant());
        Employe employe = new Employe();
        employe= employeRepository.findById(retraitDto.getEmployeId()).orElseThrow(() -> new RuntimeException("Employe not found"));
        retrait.setCompte(compte);
        retrait.setEmploye(employe);
        operationRepository.save(retrait);


        RetraitDto dto = new RetraitDto();
        dto.setNumeroOperation(retrait.getNumeroOperation());
        dto.setDateOperation(retrait.getDateOperation());
        dto.setMontant(retrait.getMontant());
        dto.setCompteId(retraitDto.getCompteId());
        return dto;
    }

    public List<OperationDto> findOperationsByClientId(Long clientId) {
        List<Operation> operationList = operationRepository.findOperationsByClientId(clientId);
        return operationList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<OperationDto> finAllOperation (){
        List<Operation> operationList = operationRepository.findAll();
        return operationList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public OperationDto mapToDto(Operation operation) {
        OperationDto dto = new OperationDto();
        dto.setNumeroOperation(operation.getNumeroOperation());
        dto.setDateOperation(operation.getDateOperation());
        dto.setMontant(operation.getMontant());
        dto.setEmployeId(operation.getEmploye().getCodeEmploye());
        dto.setEmployeName(operation.getEmploye().getNomEmploye());
        dto.setClientName(operation.getCompte().getClient().getNomClient());
        dto.setCompteId(Long.parseLong(operation.getCompte().getCodeCompte()));
        return dto;
    }

}
