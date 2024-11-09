package org.example.tp12.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OperationDto implements Serializable {
    private Long numeroOperation;
    private Date dateOperation;
    private double montant;
    private Long compteId;
    private Long employeId;
    private Long compteSource;
    private String employeName;
    private String clientName;
}
