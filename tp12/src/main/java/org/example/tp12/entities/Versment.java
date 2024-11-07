package org.example.tp12.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versment extends Operation{
    public Versment(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }
    public Versment(Date dateOperation, double montant,Compte compteid) {
        super(dateOperation, montant,compteid);
    }

    public Versment() {
        super(); // Default constructor
    }
}
