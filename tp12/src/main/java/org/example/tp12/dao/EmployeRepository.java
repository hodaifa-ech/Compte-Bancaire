package org.example.tp12.dao;

import org.example.tp12.entities.Employe;
import org.example.tp12.metier.EmployerMetierImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {
}
