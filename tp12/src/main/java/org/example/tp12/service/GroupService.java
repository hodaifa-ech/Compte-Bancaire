package org.example.tp12.service;

import org.example.tp12.Dto.GroupDto;

import org.example.tp12.metier.GroupMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupService {
    @Autowired
    private GroupMetier groupMetier;

    @PostMapping("/add")
    public GroupDto addGroupe(@RequestBody GroupDto groupeDTO) {
        return groupMetier.addGroupe(groupeDTO);
    }

    @GetMapping
    public List<GroupDto> getAllGroupes() {
        return groupMetier.getAllGroupes();
    }
}