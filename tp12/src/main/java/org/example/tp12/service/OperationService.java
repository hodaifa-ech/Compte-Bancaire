package org.example.tp12.service;

import org.example.tp12.Dto.OperationDto;
import org.example.tp12.Dto.RetraitDto;
import org.example.tp12.Dto.VersmentDto;
import org.example.tp12.entities.Operation;
import org.example.tp12.entities.Retrait;
import org.example.tp12.entities.Versment;
import org.example.tp12.metier.OperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationService {


    @Autowired
    private OperationMetier operationMetier;

    @PostMapping("/versementTo")
    public ResponseEntity<VersmentDto> versementToAmie(
          @RequestBody VersmentDto versmentDto) {
        VersmentDto result = operationMetier.versementToAmie(versmentDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/versement")
    public ResponseEntity<VersmentDto> versement(
            @RequestBody VersmentDto versmentDto) {
        // compteId et montant sont inclus dans versmentDto
        VersmentDto result = operationMetier.versement(versmentDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/retrait")
    public ResponseEntity<RetraitDto> retrait(
            @RequestBody RetraitDto retraitDto) {
        // compteId et montant sont inclus dans retraitDto
        RetraitDto result = operationMetier.retrait(retraitDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{clientId}")
    public List<OperationDto> getOperations(@PathVariable Long clientId) {
        return operationMetier.findOperationsByClientId(clientId);
    }
}
