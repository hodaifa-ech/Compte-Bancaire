package org.example.tp12.service;

import org.example.tp12.Dto.ClientDTo;
import org.example.tp12.entities.Client;
import org.example.tp12.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestService {

    @Autowired
    private ClientMetier clientMetier;

    @PostMapping
    public ResponseEntity<ClientDTo> saveClient(@RequestBody ClientDTo clientDTo) {
        clientMetier.saveClient(clientDTo);
        return ResponseEntity.ok().body(clientDTo);
    }

    @GetMapping
    public List<ClientDTo> listClient() {
        return clientMetier.listClient();
    }
}
