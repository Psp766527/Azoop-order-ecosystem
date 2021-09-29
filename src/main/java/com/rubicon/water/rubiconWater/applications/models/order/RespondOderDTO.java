package com.rubicon.water.rubiconWater.applications.models.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rubicon.water.rubiconWater.commons.Enums;
import lombok.Value;

/**
 * This RespondOderDTO provides the object to the ResponseEntity to fetch and send the order details to Client.
 */
@Value
public class RespondOderDTO {

    @JsonProperty("orderId")
    String orderId;

    @JsonProperty("orderStatus")
    Enums.OrderStatus orderStatus;

    @JsonProperty("farmerId")
    String farmerId;

    @JsonProperty("farmId")
    String farmId;

    @JsonProperty("deliveryTimeStamp")
    Long deliveryTimeStamp;

    @JsonProperty("waterDeliveryDuration")
    Integer waterDeliveryDuration;

    /**
     * This constructor sets the value of Response object by fetching details from orderContext.
     * @param orderContext it is the order details context
     */
    public RespondOderDTO(OrderDAO orderContext) {
        this.orderId = orderContext.getOrderId();
        this.orderStatus = orderContext.getOrderStatus();
        this.farmerId = orderContext.getFarmerId();
        this.farmId = orderContext.farmId;
        this.deliveryTimeStamp = orderContext.deliveryTimeStamp;
        this.waterDeliveryDuration = orderContext.getWaterDeliveryDuration();
    }
}
