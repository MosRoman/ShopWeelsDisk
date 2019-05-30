package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.Entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<Product> findAllProduct(Pageable pageable);

    Long countProduct();

    void deleteListProducts(Long[] idList);
}
