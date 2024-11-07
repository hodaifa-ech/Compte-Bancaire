package org.example.tp12.metier;

import org.example.tp12.Dto.ClientDTo;
import org.example.tp12.entities.Client;

import java.util.List;

public interface ClientMetier {

    public ClientDTo saveClient(ClientDTo clientDTo);
    public List<ClientDTo> listClient();
}