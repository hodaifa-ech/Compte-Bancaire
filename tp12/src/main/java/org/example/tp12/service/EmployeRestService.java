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
        System.out.println(employeDTO);
        return employerMetier.addEmploye(employeDTO);
    }

    @GetMapping
    public List<EmployeDto> getAllEmployes() {
       List <EmployeDto> employes = employerMetier.getAllEmployes();
       System.out.println(employes);
        return employerMetier.getAllEmployes();
    }

    @GetMapping("/{id}")
    public EmployeDto findEmploye(@PathVariable Long id) {
        return employerMetier.findEmploye(id);
    }
    @PutMapping("/{id}")
    public EmployeDto updateEmploye(@PathVariable Long id, @RequestBody EmployeDto employeDTO) {
        return employerMetier.updateEmploye(id, employeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        employerMetier.deleteEmploye(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{employeId}/assign-to-group/{groupeId}")
    public void assignEmployeToGroupe(@PathVariable Long employeId, @PathVariable Long groupeId) {
        employerMetier.assignEmployeToGroupe(employeId, groupeId);
    }
}