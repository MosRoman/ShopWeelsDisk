package com.gmail.morovo1988.DAO;

import com.gmail.morovo1988.Entity.BasketOrder;
import com.gmail.morovo1988.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Дом on 02.08.2017.
 */
public interface BasketRepository extends JpaRepository<BasketOrder, Long> {
    @Query("SELECT c FROM BasketOrder c WHERE c.user = :user and c.order is NULL" )
    List<BasketOrder> findByOrder(@Param("user") User user, Pageable pageable);

    @Query("SELECT c FROM BasketOrder c WHERE c.user = :user and c.order is NULL" )
    List<BasketOrder> findByOrder(@Param("user") User user);

    @Query("SELECT c FROM BasketOrder c WHERE c.order is not NULL" )
    List<BasketOrder> findAllOrder(Pageable pageable);
}
