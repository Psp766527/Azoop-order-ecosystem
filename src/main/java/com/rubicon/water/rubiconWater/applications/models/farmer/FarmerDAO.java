package com.rubicon.water.rubiconWater.applications.models.farmer;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

import static com.rubicon.water.rubiconWater.commons.Constants.farmerCollectionName;

/**
 * FarmerDAO class is made for data manipulation
 */
@Getter
@Service
@RequiredArgsConstructor
@Document(collection = farmerCollectionName)
public class FarmerDAO {

    @Field("_id")
    String id;

    @Field("farmerId")
    String farmerId;

    @Field("farmId")
    String farmId;

    @Field("firstName")
    String firstName;

    @Field("mobile")
    String mobile;

    @Field("emailId")
    String emailId;

}
