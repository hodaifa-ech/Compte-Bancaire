package org.example.tp12.Dto;


import java.io.Serializable;
import java.util.List;

public class EmployeDto implements Serializable {
    private Long codeEmploye;
    private String nomEmploye;
    private Long employeSupId;
    private List<Long> groupeIds;


    public Long getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(Long codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public Long getEmployeSupId() {
        return employeSupId;
    }

    public void setEmployeSupId(Long employeSupId) {
        this.employeSupId = employeSupId;
    }

    public List<Long> getGroupeIds() {
        return groupeIds;
    }

    public void setGroupeIds(List<Long> groupeIds) {
        this.groupeIds = groupeIds;
    }
}
