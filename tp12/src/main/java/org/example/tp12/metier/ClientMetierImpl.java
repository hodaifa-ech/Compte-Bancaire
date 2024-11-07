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
import java.util.Optional;
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
    @Override
    public ClientDTo updateClient(Long codeClient, ClientDTo clientDTo) {
        Optional<Client> existingClientOpt = clientRepository.findById(codeClient);
        if (existingClientOpt.isPresent()) {
            Client existingClient = existingClientOpt.get();
            existingClient.setNomClient(clientDTo.getNomClient());
            return this.mapToDTO(clientRepository.save(existingClient));
        }
        return null; // If client not found
    }

    @Override
    public boolean deleteClient(Long codeClient) {
        Optional<Client> existingClientOpt = clientRepository.findById(codeClient);
        if (existingClientOpt.isPresent()) {
            clientRepository.delete(existingClientOpt.get());
            return true; // Client deleted successfully
        }
        return false; // If client not found
    }
    private ClientDTo mapToDTO(Client client) {
        ClientDTo dto = new ClientDTo();
        dto.setCodeClient(client.getCodeClient());
        dto.setNomClient(client.getNomClient());

        return dto;
    }
}
