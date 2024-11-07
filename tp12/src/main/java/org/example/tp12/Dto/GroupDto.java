package org.example.tp12.Dto;

import java.io.Serializable;
import java.util.List;

public class GroupDto implements Serializable {
    private Long codeGroupe;
    private String nomGroupe;
    private List<Long> employeIds;

    // Getters and Setters
    public Long getCodeGroupe() {
        return codeGroupe;
    }

    public void setCodeGroupe(Long codeGroupe) {
        this.codeGroupe = codeGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public List<Long> getEmployeIds() {
        return employeIds;
    }

    public void setEmployeIds(List<Long> employeIds) {
        this.employeIds = employeIds;
    }
}
