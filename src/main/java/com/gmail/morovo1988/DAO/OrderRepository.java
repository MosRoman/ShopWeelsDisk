package com.gmail.morovo1988.DAO;

import com.gmail.morovo1988.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Дом on 18.07.2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
