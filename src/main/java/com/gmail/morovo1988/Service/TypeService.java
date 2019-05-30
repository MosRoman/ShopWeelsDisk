package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.Entity.Type;

import java.util.List;

public interface TypeService {
    void addType(Type type);

    List<Type> findAllTypes();

    Long countByType(Type type);

    Type findTypeById(Long id);
}
