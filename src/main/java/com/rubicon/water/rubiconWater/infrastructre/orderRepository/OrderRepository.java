package com.rubicon.water.rubiconWater.infrastructre.orderRepository;

import com.rubicon.water.rubiconWater.domains.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The OrderRepository interface helps to persist Order class object in Mongodb.
 * This interface is inheriting MongoRepository to perform CRUD operation by using predefined methods in
 * MongoRepository
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
