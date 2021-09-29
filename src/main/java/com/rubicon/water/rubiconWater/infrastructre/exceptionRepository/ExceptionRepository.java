package com.rubicon.water.rubiconWater.infrastructre.exceptionRepository;

import com.rubicon.water.rubiconWater.infrastructre.models.ExceptionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The ExceptionRepository interface helps to persist ExceptionModel object in Mongodb.
 * This interface is inheriting MongoRepository to perform CRUD operation by using predefined methods in
 * MongoRepository
 */
@Repository
public interface ExceptionRepository extends MongoRepository<ExceptionModel, String> {

}
