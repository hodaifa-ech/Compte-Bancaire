package org.example.tp12.dao;

import org.example.tp12.entities.Client;
import org.example.tp12.entities.Compte;
import org.example.tp12.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
    List<Compte> findByClient(Client client);
}
