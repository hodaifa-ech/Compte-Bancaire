package org.example.tp12.metier;

import org.example.tp12.Dto.GroupDto;
import org.example.tp12.entities.Client;
import org.example.tp12.entities.Groupe;

import java.util.List;
import java.util.Optional;

public interface GroupMetier {

    public List<GroupDto> getAllGroupes();
    public GroupDto addGroupe(GroupDto groupeDTO);
    public GroupDto updateGroup(Long id, GroupDto groupDto);
    public void deleteGroup(Long id);
}
