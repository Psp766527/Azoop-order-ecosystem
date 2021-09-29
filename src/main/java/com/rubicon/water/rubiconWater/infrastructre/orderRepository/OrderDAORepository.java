package com.rubicon.water.rubiconWater.infrastructre.orderRepository;

import com.rubicon.water.rubiconWater.applications.models.order.OrderDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAORepository extends MongoRepository<OrderDAO,String> {

    @Query("{farmId: ?0}")
    OrderDAO findByFarmId(String farmId);

    @Query("{orderId: ?0}")
    OrderDAO findByOrderId(String orderId);
}
