package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.dto.JsonMessage;

import java.util.List;

public interface CrudInterface<T> {

    List<T> findAllElements();
    JsonMessage addElement(T elementDto);
    T findElementById(String id);
    JsonMessage editElement( T elementDto);
    JsonMessage deleteElement( String id);
}
