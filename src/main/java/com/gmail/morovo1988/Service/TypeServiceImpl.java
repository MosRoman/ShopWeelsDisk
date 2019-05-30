package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.DAO.TypeRepository;
import com.gmail.morovo1988.Entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void addType(Type type) {
        this.typeRepository.save(type);
    }

    @Override
    public List<Type> findAllTypes() {
        return this.typeRepository.findAll();
    }

    @Override
    public Long countByType(Type type) {
        return this.typeRepository.countByType(type);
    }

    @Override
    public Type findTypeById(Long id) {
        return this.typeRepository.findOne(id);
    }
}
