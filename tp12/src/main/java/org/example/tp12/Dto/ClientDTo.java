package org.example.tp12.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDTo implements Serializable {
    private Long codeClient;
    private String nomClient;

}
