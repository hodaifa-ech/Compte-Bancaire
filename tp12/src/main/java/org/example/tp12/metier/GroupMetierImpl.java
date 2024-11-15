package org.example.tp12.metier;


import org.example.tp12.Dto.GroupDto;
import org.example.tp12.dao.GroupRepository;
import org.example.tp12.entities.Employe;
import org.example.tp12.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMetierImpl implements GroupMetier {

    @Autowired
    private GroupRepository groupeRepository;

    public GroupDto addGroupe(GroupDto groupeDTO) {
        Groupe groupe = new Groupe();
        groupe.setNomGroupe(groupeDTO.getNomGroupe());

        Groupe savedGroupe = groupeRepository.save(groupe);
        return mapToDTO(savedGroupe);
    }

    public List<GroupDto> getAllGroupes() {
        return groupeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public GroupDto updateGroup(Long id, GroupDto groupDto) {
        Groupe existingGroup = groupeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id " + id));

        existingGroup.setNomGroupe(groupDto.getNomGroupe());

        Groupe updatedGroup = groupeRepository.save(existingGroup);
        return mapToDTO(updatedGroup);
    }

    public Long getGroupCount(){
        return groupeRepository.count();
    }
    public void deleteGroup(Long id) {
        Groupe group = groupeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id " + id));
        groupeRepository.delete(group);
    }
    private GroupDto mapToDTO(Groupe groupe) {
        GroupDto dto = new GroupDto();
        dto.setCodeGroupe(groupe.getCodeGroupe());
        dto.setNomGroupe(groupe.getNomGroupe());
        if(groupe.getEmploye() != null){
            dto.setEmployeIds(
                    groupe.getEmploye().stream()
                            .map(Employe::getCodeEmploye)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
