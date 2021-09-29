package com.rubicon.water.rubiconWater.domains.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.rubicon.water.rubiconWater.commons.Constants.farmerCollectionName;

/**
 * This Farmer entity class is for providing farmer domain ,it contains attributes of the farmer.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = farmerCollectionName)
public class Farmer {

    @Id
    String id;

    @Indexed(unique = true)
    String farmerId;

    @Indexed(unique = true)
    String farmId;

    String firstName;

    String middleName;

    String lastName;

    @Indexed(unique = true)
    String mobile;

    @Indexed(unique = true)
    String emailId;

    String availableSlot;

    /**
     * this the parameterized constructor which will help to create farmer class object
     *
     * @param farmerId      the id of the farmer which is generated at the time of farmer register it-self
     * @param farmId        the id of the farm which is mapped to specific farmer
     * @param firstName     firstName of the farmer
     * @param middleName    middleName of the farmer
     * @param lastName      lastName of the farmer
     * @param mobile        contact no of the farmer
     * @param emailId       emailId of the farmer
     * @param availableSlot the delivery slots in which delivery service is open to serve
     */
    public Farmer(String farmerId, String farmId, String firstName, String middleName, String lastName, String mobile,
                  String emailId, String availableSlot) {
        this.id = farmerId;
        this.farmerId = farmerId;
        this.farmId = farmId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.emailId = emailId;
        this.availableSlot = availableSlot;
    }

    /**
     * This static factory inner class will help to create the Farmer object
     * to persist it in DataBase
     */
    public static class Factory {
        /**
         * this static method will return the object of the farmer class.
         *
         * @param farmerId      the id of the farmer which is generated at the time of farmer register it-self
         * @param farmId        the id of the farm which is mapped to specific farmer
         * @param firstName     firstName of the farmer
         * @param middleName    middleName of the farmer
         * @param lastName      lastName of the farmer
         * @param mobile        contact no of the farmer
         * @param emailId       emailId of the farmer
         * @param availableSlot the delivery slots in which delivery service is open to serve
         * @return it returns FarmerClass object
         */
        public static Farmer createFarmer(String farmerId, String farmId, String firstName, String middleName,
                                          String lastName,String mobile,String emailId, String availableSlot) {

            return new Farmer(farmerId, farmId, firstName, middleName, lastName,mobile,emailId, availableSlot);
        }
    }
}
