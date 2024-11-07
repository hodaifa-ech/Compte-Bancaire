package org.example.tp12.metier;

import org.example.tp12.Dto.EmployeDto;
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
    private EmployeDto mapToDTO(Employe employe) {
        EmployeDto dto = new EmployeDto();

        dto.setCodeEmploye(employe.getCodeEmploye());
        dto.setNomEmploye(employe.getNomEmploye());
        dto.setEmployeSupId(employe.getEmployeSup() != null ? employe.getEmployeSup().getCodeEmploye() : null);

        return dto;
    }
}
