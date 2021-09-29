package com.rubicon.water.rubiconWater.commons;


import java.time.Instant;

/**
 * This Helper class contains functions to help the other class for common functionality.
 */

public class Helper {

    /**
     * This public static function is for generating farmerId on the basis of currentTimeStamp and farmerName
     *
     * @param farmerName name of the farmer who is registering.
     * @return it returns farmerId to the calling functions .
     */
    public static String generateFarmerId(String farmerName) {

        return Instant.now().getEpochSecond() + "FAR" + farmerName;
    }

    /**
     * This public static function is for generating farmId with the help of farmerId and timeStamp .
     *
     * @param farmerId The farmerId
     * @return it returns the farmId to the calling functions.
     */
    public static String generateFarmId(String farmerId) {

        return Instant.now().getEpochSecond() + "_FARM_" + farmerId;
    }

    /**
     * This static function returns orderId on the basis of farmId and farmerId and currentTimeStamp
     *
     * @param farmId   the id of the farm for which farmer is placing order
     * @return in returns orderId
     */
    public static String generateOrderId( String farmId) {

        return Instant.now().getEpochSecond() + "_OR_" + farmId;
    }

    /**
     * This public static function is for providing slots option for delivery
     *
     * @return delivery slots string to the calling method
     */
    public static String deliverySlots() {
        return "time slots";
    }

    /**
     * This public static function is for converting epoch Timestamp to date and time.
     *
     * @param timeStamp timeStamp to convert to date and time
     * @return it returns date and time to calling function.
     */
    public static String getTime(long timeStamp) {

        return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(timeStamp * 1000));
    }





}
