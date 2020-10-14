package com.sifontes.Qmanagerv2.repository;

import com.sifontes.Qmanagerv2.model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PartidoRepository extends MongoRepository<Partido,Serializable> {
}
