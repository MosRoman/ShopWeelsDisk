package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.DAO.BasketRepository;
import com.gmail.morovo1988.Entity.BasketOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void deleteBasketOrder(BasketOrder basketOrder) {
        basketRepository.delete(basketOrder);
    }

    @Override
    public void addBasketOrder(BasketOrder basketOrder) {
        basketRepository.save(basketOrder);
    }
}
