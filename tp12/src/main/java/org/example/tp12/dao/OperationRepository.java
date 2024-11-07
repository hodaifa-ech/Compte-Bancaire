package org.example.tp12.dao;

import org.example.tp12.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.compte.client.codeClient = :clientId")
    List<Operation> findOperationsByClientId(@Param("clientId") Long clientId);
}
