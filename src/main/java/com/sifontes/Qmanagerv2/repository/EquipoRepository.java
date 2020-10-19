package com.sifontes.Qmanagerv2.repository;

import com.sifontes.Qmanagerv2.model.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EquipoRepository extends MongoRepository<Equipo, Serializable> {

    @Query(value = "{'nombre': {$regex : '^?0$', $options: 'i'}}")//TODO: mejorar la query para buscar sin case sensitive
     Equipo findByName(String nombre);
}
