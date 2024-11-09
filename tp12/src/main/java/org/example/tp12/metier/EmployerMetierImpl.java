package org.example.tp12.metier;

import org.example.tp12.Dto.EmployeDto;
import org.example.tp12.dao.CompteRepository;
import org.example.tp12.dao.EmployeRepository;
import org.example.tp12.dao.GroupRepository;
import org.example.tp12.entities.Employe;
import org.example.tp12.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerMetierImpl  implements  EmployerMetier{

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private GroupRepository groupeRepository;
    @Autowired
    private CompteRepository compteRepository;

    public EmployeDto addEmploye(EmployeDto employeDTO) {
        Employe employe = new Employe();
        employe.setNomEmploye(employeDTO.getNomEmploye());

        if (employeDTO.getEmployeSupId() != null) {
            Employe supervisor = employeRepository.findById(employeDTO.getEmployeSupId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
            employe.setEmployeSup(supervisor);
        }

        if (employeDTO.getGroupeIds() != null) {
            List<Groupe> groupes = groupeRepository.findAllById(employeDTO.getGroupeIds());
            employe.setGroupes(groupes);
        }

        Employe savedEmploye = employeRepository.save(employe);
        return mapToDTO(savedEmploye);
    }

    public List<EmployeDto> getAllEmployes() {
        return employeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public void assignEmployeToGroupe(Long employeId, Long groupeId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employe not found"));
        Groupe groupe = groupeRepository.findById(groupeId)
                .orElseThrow(() -> new RuntimeException("Groupe not found"));

        employe.getGroupes().add(groupe);
        groupe.getEmploye().add(employe);

        employeRepository.save(employe);
        groupeRepository.save(groupe);
    }
    public EmployeDto findEmploye(Long employeId) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employe not found"));
        return mapToDTO(employe);
    }

    public EmployeDto updateEmploye(Long id, EmployeDto employeDTO) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe not found"));

        employe.setNomEmploye(employeDTO.getNomEmploye());

        if (employeDTO.getEmployeSupId() != null) {
            Employe supervisor = employeRepository.findById(employeDTO.getEmployeSupId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
            employe.setEmployeSup(supervisor);
        }

        if (employeDTO.getGroupeIds() != null) {
            List<Groupe> groupes = groupeRepository.findAllById(employeDTO.getGroupeIds());
            employe.setGroupes(groupes);
        }

        Employe updatedEmploye = employeRepository.save(employe);
        return mapToDTO(updatedEmploye);
    }

    public void deleteEmploye(Long id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe not found"));

        employeRepository.delete(employe);
    }
    private EmployeDto mapToDTO(Employe employe) {
        EmployeDto dto = new EmployeDto();
        // Map each Groupe to its ID and set it as groupeIds in the DTO

        if(employe.getGroupes() != null) {
            dto.setGroupeIds(
                    employe.getGroupes().stream()
                            .map(Groupe::getCodeGroupe)
                            .collect(Collectors.toList())
            );
            dto.setGroupeNames(
                    employe.getGroupes().stream()
                            .map(Groupe::getNomGroupe)
                            .collect(Collectors.toList())
            );
        }

        dto.setCodeEmploye(employe.getCodeEmploye());
        dto.setNomEmploye(employe.getNomEmploye());
        dto.setEmployeSupId(employe.getEmployeSup() != null ? employe.getEmployeSup().getCodeEmploye() : null);
        dto.setEmployeSupName(employe.getEmployeSup()!=null?employe.getEmployeSup().getNomEmploye():null);

        return dto;
    }
}

