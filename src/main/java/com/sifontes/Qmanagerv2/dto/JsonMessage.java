package com.sifontes.Qmanagerv2.dto;

import java.io.Serializable;

public class JsonMessage implements Serializable {

    private boolean success;
    private String message;


    public JsonMessage(boolean success, String message){
        this.success = success; this.message = message;
    }

    public JsonMessage(String message, Exception e ){
        this.success = false; this.message = String.format("%s: [%s]",message,e.getMessage());
    }

    public boolean getSuccess() { return this.success; }
    public String getMessage() { return this.message; }

}
