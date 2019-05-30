package com.gmail.morovo1988.DAO;

import com.gmail.morovo1988.Entity.Product;
import com.gmail.morovo1988.Entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Дом on 18.07.2017.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT c FROM Product c WHERE c.type = :type")
    List<Product> findByType(@Param("type") Type type, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Product c WHERE c.type = :type")
    long countByType(@Param("type") Type type);

//    @Query("SELECT c FROM Product c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
//    List<Product> findByPattern(@Param("pattern") String pattern, Pageable pageable);
}
