package org.example.tp12.metier;

import org.example.tp12.Dto.EmployeDto;
import org.example.tp12.entities.Employe;
import org.example.tp12.entities.Groupe;

import java.util.List;
import java.util.Optional;

public interface EmployerMetier {

    public EmployeDto addEmploye(EmployeDto employeDTO);
    public List<EmployeDto> getAllEmployes();
    public void assignEmployeToGroupe(Long employeId, Long groupeId);
    public EmployeDto findEmploye(Long employeId);
    public void deleteEmploye(Long id);
    public EmployeDto updateEmploye(Long id, EmployeDto employeDTO);
}
