package org.example.tp12.Dto;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EmployeDto implements Serializable {
    private Long codeEmploye;
    private String nomEmploye;
    private String EmployeSupName;
    private Long employeSupId;
    private List<Long> groupeIds;
    private List<String> groupeNames;



}
