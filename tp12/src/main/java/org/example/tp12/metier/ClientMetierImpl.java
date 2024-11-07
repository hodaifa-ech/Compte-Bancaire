package org.example.tp12.metier;

import org.example.tp12.Dto.ClientDTo;
import org.example.tp12.Dto.EmployeDto;
import org.example.tp12.dao.ClientRepository;
import org.example.tp12.entities.Client;
import org.example.tp12.entities.Employe;
import org.example.tp12.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMetierImpl implements ClientMetier  {

    @Autowired
    private  ClientRepository clientRepository;

    @Override
    public ClientDTo saveClient(ClientDTo clientDTo) {
        Client client = new Client();
        client.setNomClient( clientDTo.getNomClient());

         return this.mapToDTO( clientRepository.save(client));
    }

    @Override
    public List<ClientDTo> listClient() {

        return clientRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ClientDTo mapToDTO(Client client) {
        ClientDTo dto = new ClientDTo();
        dto.setCodeClient(client.getCodeClient());
        dto.setNomClient(client.getNomClient());

        return dto;
    }
}
