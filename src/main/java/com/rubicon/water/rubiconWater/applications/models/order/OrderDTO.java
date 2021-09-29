package com.rubicon.water.rubiconWater.applications.models.order;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.rubicon.water.rubiconWater.commons.Constants.orderCollectionName;

/**
 * The OrderDTO class is to take request context from client and validate the request context.
 */
@Getter
@RequiredArgsConstructor
@Document(collection = orderCollectionName)
public class OrderDTO {


    @JsonProperty("farmId")
    String farmId;

    @JsonProperty("deliveryTimeStamp")
    Long deliveryTimeStamp;

    @JsonProperty("waterDeliveryDuration")
    Integer waterDeliveryDuration;

    /**
     * This functions is for validating OrderDTO model .
     * This function return 'true' if and only if , all the conditions are false
     *
     * @return boolean value which is "true".
     */

    public boolean isValid() {

        if (farmId == null || farmId.trim().isEmpty())
            throw new IllegalArgumentException("The farmId can not be null or empty");

        if (deliveryTimeStamp == null || deliveryTimeStamp.toString().trim().isEmpty())
            throw new IllegalArgumentException("The deliveryTimeStamp can not be null or empty");

        if (waterDeliveryDuration == null || waterDeliveryDuration.toString().trim().isEmpty() || waterDeliveryDuration <= 0)
            throw new IllegalArgumentException("The water delivery duration can not be null or 0 (Zero) or less then 0");


        return true;
    }


}
