package org.example.tp12.service;

import org.example.tp12.Dto.OperationDto;
import org.example.tp12.Dto.RetraitDto;
import org.example.tp12.Dto.VersmentDto;
import org.example.tp12.entities.Operation;
import org.example.tp12.entities.Retrait;
import org.example.tp12.entities.Versment;
import org.example.tp12.metier.OperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationService {


    @Autowired
    private OperationMetier operationMetier;

    @PostMapping("/versementTo")
    public ResponseEntity<VersmentDto> versementToAmie(@RequestBody VersmentDto versmentDto) {
        try {
            VersmentDto result = operationMetier.versementToAmie(versmentDto);
            return ResponseEntity.ok(result); // Status 200 OK for success
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to complete virement", e); // Status 400 for failure
        }
    }
    @GetMapping("/count")
    public Long getOpertionCount() {
        return operationMetier.getcountOperation();
    }
    @PostMapping("/versement")
    public ResponseEntity<VersmentDto> versement(@RequestBody VersmentDto versmentDto) {
        try {
            VersmentDto result = operationMetier.versement(versmentDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to complete versement", e);
        }
    }

    @PostMapping("/retrait")
    public ResponseEntity<RetraitDto> retrait(@RequestBody RetraitDto retraitDto) {
        try {
            RetraitDto result = operationMetier.retrait(retraitDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance for retrait", e);
        }
    }
    @GetMapping
    public List<OperationDto> getAllOperations() {
        return operationMetier.finAllOperation();
    }

    @GetMapping("/{clientId}")
    public List<OperationDto> getOperations(@PathVariable Long clientId) {
        return operationMetier.findOperationsByClientId(clientId);
    }
}
