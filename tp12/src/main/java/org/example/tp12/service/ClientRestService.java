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

    // Update Client
    @PutMapping("/{codeClient}")
    public ResponseEntity<ClientDTo> updateClient(@PathVariable Long codeClient, @RequestBody ClientDTo clientDTo) {
        ClientDTo updatedClient = clientMetier.updateClient(codeClient, clientDTo);
        if (updatedClient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedClient);
    }

    // Delete Client
    @DeleteMapping("/{codeClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long codeClient) {
        boolean isDeleted = clientMetier.deleteClient(codeClient);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<ClientDTo> listClient() {
        return clientMetier.listClient();
    }
}
