package com.rubicon.water.rubiconWater.commons;

/**
 * The Enums class is for maintaining the re-usable constants
 */
public class Enums {

    public enum DeliverySlots{

    }

    /**
     * The OrderStatus changes on the basis of client request
     */
    public enum OrderStatus{
        Requested,
        InProgress,
        Delivered,
        Cancelled
    }
}
