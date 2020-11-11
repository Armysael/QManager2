package com.sifontes.Qmanagerv2.repository;

import com.sifontes.Qmanagerv2.model.Equipo;
import com.sifontes.Qmanagerv2.model.Quiniela;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface QuinielaRepository extends MongoRepository<Quiniela, Serializable> {

    @Query(value = "{'eventId': ?0}")
    List<Quiniela> findByEvent(long eventoId);
}
