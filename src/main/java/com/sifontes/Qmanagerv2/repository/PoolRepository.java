package com.sifontes.Qmanagerv2.repository;

import com.sifontes.Qmanagerv2.model.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface PoolRepository extends MongoRepository<Pool,Serializable> {
}
