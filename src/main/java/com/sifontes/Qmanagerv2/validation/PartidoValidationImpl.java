package com.sifontes.Qmanagerv2.validation;

import com.sifontes.Qmanagerv2.dto.EquipoDto;
import com.sifontes.Qmanagerv2.dto.PartidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PartidoValidationImpl {


    public boolean validateBeforeAdd(PartidoDto partidoDto){

        //validar que los equipos existan
        long idAux;

        List<EquipoDto> listaEquipo = partidoDto.getListaEquipo();


        Set<EquipoDto> collect = listaEquipo.stream().collect(Collectors.toSet());

        if(collect.size()!=listaEquipo.size()){
            return false;
        }

        return true;
        //validar que no este duplicado el equipo en lista
        //validar que mismo partido (equipos + fecha + evento) no exista
    }



    public void validateBeforeDeleteOrModify(){

        //validar que no este en un evento activo
        //validar que no este en una quiniela activa

    }

}
