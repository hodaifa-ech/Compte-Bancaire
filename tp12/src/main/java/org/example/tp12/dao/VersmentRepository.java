package org.example.tp12.dao;


import org.example.tp12.entities.Versment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersmentRepository  extends JpaRepository<Versment, Long> {
}
