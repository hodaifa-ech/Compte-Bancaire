package org.example.tp12.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Data
public class CompteDto implements Serializable {
    private String codeCompte;
    private Date dateCreation;
    private double solde;
    private String type; // "CC" or "CE"
    private Long clientId;
    private Long employeId;
    private double taux; // For CompteEpargne
    private double decouvert; // For CompteCourant
    private String nomClient;
    private String nomEmploye;
}
