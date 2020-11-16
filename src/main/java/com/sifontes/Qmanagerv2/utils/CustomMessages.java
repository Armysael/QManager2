package com.sifontes.Qmanagerv2.utils;

import com.sifontes.Qmanagerv2.dto.JsonMessage;
import org.springframework.stereotype.Component;

@Component
public class CustomMessages {


    public JsonMessage getActionSuccessMessage(EnumEntidades enumEntidades, EnumAcciones enumAcciones){

        return new JsonMessage(true,enumEntidades.retornarNombreEntidad()+enumAcciones.retornarMensaje()+"con éxito");
    }

    public JsonMessage getActionErrorMessage(EnumEntidades enumEntidades,EnumAcciones enumAcciones, Exception e ){

        return new JsonMessage("Error"+enumAcciones.retornarMensaje()+ enumEntidades.retornarNombreEntidad()+": ", e);
    }

}
