package org.example.tp12.service;

import org.example.tp12.Dto.EmployeDto;
import org.example.tp12.entities.Employe;
import org.example.tp12.entities.Groupe;
import org.example.tp12.metier.EmployerMetier;
import org.example.tp12.metier.GroupMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employees")
public class EmployeRestService {
    @Autowired
    private EmployerMetier employerMetier;

    @PostMapping("/add")
    public EmployeDto addEmploye(@RequestBody EmployeDto employeDTO) {
        return employerMetier.addEmploye(employeDTO);
    }

    @GetMapping
    public List<EmployeDto> getAllEmployes() {
        return employerMetier.getAllEmployes();
    }

    @GetMapping("/{id}")
    public EmployeDto findEmploye(@PathVariable Long id) {
        return employerMetier.findEmploye(id);
    }

    @PostMapping("/{employeId}/assign-to-group/{groupeId}")
    public void assignEmployeToGroupe(@PathVariable Long employeId, @PathVariable Long groupeId) {
        employerMetier.assignEmployeToGroupe(employeId, groupeId);
    }
}