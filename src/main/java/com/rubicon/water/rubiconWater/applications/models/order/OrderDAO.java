package com.rubicon.water.rubiconWater.applications.models.order;


import com.rubicon.water.rubiconWater.commons.Enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

import static com.rubicon.water.rubiconWater.commons.Constants.orderCollectionName;

/**
 * This is the OrderDAO class which is used to fetch the order model from the mongodb.
 */
@Getter
@Service
@RequiredArgsConstructor
@Document(collection = orderCollectionName)
public class OrderDAO {

    @Id
    String id;

    @Field("orderId")
    String orderId;

    @Field("orderStatus")
    Enums.OrderStatus orderStatus;

    @Field("farmerId")
    String farmerId;

    @Field("farmId")
    String farmId;

    @Field("deliveryTimeStamp")
    long deliveryTimeStamp;

    @Field("waterDeliveryDuration")
    int waterDeliveryDuration;


    /**
     * This function trigger the event to update the status of order if farmer cancels the order
     */
    public void cancelOrder() {
        this.orderStatus = Enums.OrderStatus.Cancelled;
    }

    /**
     * This function will update the deliveryTimeStamp on the request of the farmer .
     *
     * @param deliveryTimeStamp new Delivery Time stamp .
     */
    public void updateDeliveryTime(long deliveryTimeStamp) {
        this.deliveryTimeStamp = deliveryTimeStamp;
    }

    /**
     * this function update the orderStatus as inProgress if order is not cancelled or not delivered or not InProgress
     */
    public void markAsInProgress(Enums.OrderStatus status) {
        if (this.orderStatus !=status || status!= Enums.OrderStatus.Cancelled)
            this.orderStatus = Enums.OrderStatus.InProgress;
    }

    public void markAsDelivered() {
        this.orderStatus = Enums.OrderStatus.Delivered;
    }


}
