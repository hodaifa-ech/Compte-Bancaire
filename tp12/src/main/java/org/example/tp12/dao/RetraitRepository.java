package org.example.tp12.dao;


import org.example.tp12.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetraitRepository extends JpaRepository <Retrait, Long> {
}
