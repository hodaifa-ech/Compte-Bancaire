package org.example.tp12.metier;

import org.example.tp12.Dto.OperationDto;
import org.example.tp12.Dto.RetraitDto;
import org.example.tp12.Dto.VersmentDto;
import org.example.tp12.entities.*;

import java.util.List;

public interface OperationMetier {

    public VersmentDto versement(VersmentDto versmentDto);
    public VersmentDto versementToAmie(VersmentDto versmentDto);
    public RetraitDto retrait(RetraitDto retraitDto);
    public List<OperationDto> findOperationsByClientId(Long clientId);

}
