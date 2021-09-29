package com.rubicon.water.rubiconWater.applications.controller;


import com.rubicon.water.rubiconWater.applications.exceptions.BadRequestException;
import com.rubicon.water.rubiconWater.applications.models.farmer.FarmerDAO;
import com.rubicon.water.rubiconWater.applications.models.order.OrderDAO;
import com.rubicon.water.rubiconWater.applications.models.order.OrderDTO;
import com.rubicon.water.rubiconWater.applications.models.order.RespondOderDTO;
import com.rubicon.water.rubiconWater.commons.Enums;
import com.rubicon.water.rubiconWater.commons.Helper;
import com.rubicon.water.rubiconWater.domains.entities.Order;
import com.rubicon.water.rubiconWater.infrastructre.farmerRepository.FarmerDAOMongoRepository;
import com.rubicon.water.rubiconWater.infrastructre.orderRepository.OrderDAORepository;
import com.rubicon.water.rubiconWater.infrastructre.orderRepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;

/**
 * The OrderController class will help to client to place order for water delivery.
 * It also manages the order to cancel the order and many-more .
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    private final FarmerDAOMongoRepository farmerDAOMongoRepository;

    private final OrderDAORepository orderDAORepository;


    /**
     * The placeOrder controller will help to place water order successfully .
     *
     * @param orderDTOContext the requestContext which is received by client .
     * @return it returns the response to client on the basis of order status.
     */
    @PostMapping(value = "/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTOContext) {

        try {

            if (!orderDTOContext.isValid())
                throw new BadRequestException("can not place order because orderContext in not valid");

            if (orderDTOContext.getDeliveryTimeStamp() < Instant.now().getEpochSecond())
                throw new BadRequestException("The deliveryTimeStamp can not be less than current timeStamp");

            FarmerDAO farmerContext = farmerDAOMongoRepository.fetchByFarmId(orderDTOContext.getFarmId());

            if (farmerContext == null)
                throw new BadRequestException("can not place order because farmId does not exists for any farmer : [" +
                        orderDTOContext.getFarmId() + "]");

            OrderDAO orderContext = orderDAORepository.findByFarmId(orderDTOContext.getFarmId());

            //This condition check if farmer already placed order in the given timeStamp
            if (orderDTOContext.getDeliveryTimeStamp() < (orderContext.getDeliveryTimeStamp() +
                    (long) orderContext.getWaterDeliveryDuration() * 60 * 60)) {
                throw new BadRequestException("You have already requested water delivery between " +
                        Helper.getTime(orderContext.getDeliveryTimeStamp()) + " to " +
                        Helper.getTime((long) orderContext.getWaterDeliveryDuration() * 60 * 60 + orderContext.getDeliveryTimeStamp()));
            }

            String orderId = Helper.generateOrderId( orderDTOContext.getFarmId());

            Order orderPayload = Order.OrderFactory.createOder(orderId, Enums.OrderStatus.Requested, farmerContext.getFarmerId(),
                    farmerContext.getFarmId(), orderDTOContext.getDeliveryTimeStamp(), orderDTOContext.getWaterDeliveryDuration());

            orderRepository.save(orderPayload);

            return ResponseEntity.created(URI.create("OrderId:" + orderId)).build();

        } catch (BadRequestException | IllegalArgumentException b) {

            log.error("Exception raised not able to place order successfully " + b);

            return ResponseEntity.badRequest().header("message", b.getMessage())
                    .contentType(MediaType.APPLICATION_JSON).build();
        } catch (DuplicateKeyException duplicateKeyException) {
            return ResponseEntity.badRequest().header("message", "order all-ready placed at the given time"
                            + orderDTOContext.getDeliveryTimeStamp())
                    .contentType(MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * This controller will mark the status cancelled when client request for order cancellation
     *
     * @param orderId the id of the order which was generated when order was Placed.
     * @return it returns accepted request for order Cancellation.
     */
    @PutMapping(value = "/order/cancel")
    public ResponseEntity<?> cancelOrderById(@RequestParam(value = "orderId") String orderId) {

        try {


            OrderDAO orderContext = orderDAORepository.findByOrderId(orderId);

            if (orderContext == null)
                throw new BadRequestException("No order is exists for the provided orderId : [ " + orderId + " ]");


            if (orderContext.getOrderStatus() == Enums.OrderStatus.Cancelled)
                throw new BadRequestException("Order is already cancelled , orderId :"+orderContext.getOrderId());

            if(orderContext.getOrderStatus() == Enums.OrderStatus.Delivered)
                throw new BadRequestException("Order is already been delivered , Not able to proceed the cancellation request ,orderId :"
                        +orderContext.getOrderId());

            //this will mark orderStatus as cancelled
            orderContext.cancelOrder();

            orderDAORepository.save(orderContext);

            return ResponseEntity.ok(URI.create("orderCancelled:" + orderContext.getOrderId()));

        } catch (BadRequestException | IllegalArgumentException b) {

            return ResponseEntity.badRequest().header("message", b.getMessage())
                    .contentType(MediaType.APPLICATION_JSON).build();

        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> fetchOrderById(@PathVariable(value = "orderId")String orderId){

        try {

            if(orderId == null || orderId.trim().isEmpty())
                throw new IllegalArgumentException("orderId can not be null or empty.");

            OrderDAO orderContext= orderDAORepository.findByOrderId(orderId);

            if(orderContext == null)
                return ResponseEntity.noContent().header("message",
                        "No order found for the passed orderId :"+orderId).build();

            return ResponseEntity.ok(new RespondOderDTO(orderContext));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage())
                    .contentType(MediaType.APPLICATION_JSON).build();
        }
    }
}
