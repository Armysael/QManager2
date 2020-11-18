package com.sifontes.Qmanagerv2.service;

import com.sifontes.Qmanagerv2.utils.JsonMessage;

import java.util.List;

public interface CrudInterface<T> {

    List<T> findAllElements();
    JsonMessage addElement(T elementDto);
    T findElementById(long id);
    JsonMessage editElement( T elementDto);
    JsonMessage deleteElement( long id);
}
