package com.gmail.morovo1988.DAO;

import com.gmail.morovo1988.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("SELECT COUNT(c) FROM Product c WHERE c.type = :type")
    long countByType(@Param("type") Type type);
}
