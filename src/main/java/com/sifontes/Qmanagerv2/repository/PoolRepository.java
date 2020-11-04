package com.sifontes.Qmanagerv2.repository;

import com.sifontes.Qmanagerv2.model.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PoolRepository extends MongoRepository<Pool,Serializable> {
}
