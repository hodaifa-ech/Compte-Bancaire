package org.example.tp12.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class GroupDto implements Serializable {
    private Long codeGroupe;
    private String nomGroupe;
    private List<Long> employeIds;


}
