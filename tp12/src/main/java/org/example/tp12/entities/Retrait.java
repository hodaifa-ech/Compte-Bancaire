package org.example.tp12.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
    public Retrait(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }
    public Retrait(Date dateOperation, double montant,Compte compteid) {
        super(dateOperation, montant,compteid);
    }
    public Retrait() {
        super(); // Default constructor
    }
}