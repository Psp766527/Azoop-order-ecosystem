package com.rubicon.water.rubiconWater.infrastructre.farmerRepository;

import com.rubicon.water.rubiconWater.domains.entities.Farmer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * The FarmerRepository interface helps to persist Farmer class object in Mongodb.
 * This interface is inheriting MongoRepository to perform CRUD operation by using predefined methods in
 * MongoRepository interface.
 */
@Repository
public interface FarmerRepository extends MongoRepository<Farmer,String> {

}
