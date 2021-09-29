package com.rubicon.water.rubiconWater.cronJobs;


import com.rubicon.water.rubiconWater.applications.models.order.OrderDAO;
import com.rubicon.water.rubiconWater.commons.Enums;
import com.rubicon.water.rubiconWater.infrastructre.orderRepository.OrderDAORepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DeliveryCronJob {

    private final OrderDAORepository orderDAORepository;

    public DeliveryCronJob(OrderDAORepository orderDAORepository) {
        this.orderDAORepository = orderDAORepository;
    }


    @Scheduled(fixedDelay = 1000)
    private void simulateWater() {

        for (OrderDAO orderContext : orderDAORepository.findAll()) {
            if (!orderContext.getOrderStatus().equals(Enums.OrderStatus.InProgress)
                    && orderContext.getDeliveryTimeStamp() <= Instant.now().getEpochSecond()) {
                orderContext.markAsInProgress(orderContext.getOrderStatus());

                if (orderContext.getOrderStatus().equals(Enums.OrderStatus.InProgress)) {
                    orderDAORepository.save(orderContext);

                    System.out.println("Water delivery to farmId " + orderContext.getFarmId() + "  started");
                }
            } else if (orderContext.getOrderStatus().equals(Enums.OrderStatus.InProgress)) {
                System.out.println("WaterOrder delivery is inProgress");
            } else if (orderContext.getOrderStatus().equals(Enums.OrderStatus.Cancelled)) {
                System.out.println("WaterOrder can not deliver it is cancelled");
            } else if (orderContext.getOrderStatus().equals(Enums.OrderStatus.Delivered)) {
                System.out.println("Already delivered");
            } else if (orderContext.getOrderStatus().equals(Enums.OrderStatus.Requested)) {
                System.out.println("New water order for farmId  " + orderContext.getFarmId() + " created");
            } else if ((orderContext.getDeliveryTimeStamp() + (long) orderContext.getWaterDeliveryDuration() * 60 * 60)
                    <= Instant.now().getEpochSecond())
                System.out.println("Water delivery to farmId" + orderContext.getFarmId() + "  stopped");
        }
    }
}
