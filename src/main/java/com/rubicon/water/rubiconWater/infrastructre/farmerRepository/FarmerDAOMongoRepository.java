package com.rubicon.water.rubiconWater.infrastructre.farmerRepository;

import com.rubicon.water.rubiconWater.applications.models.farmer.FarmerDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerDAOMongoRepository extends MongoRepository<FarmerDAO,String> {

    @Query("{farmId: ?0}")
        FarmerDAO fetchByFarmId(String farmId);
}
