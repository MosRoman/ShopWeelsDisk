package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.DAO.BasketRepository;
import com.gmail.morovo1988.DAO.OrderRepository;
import com.gmail.morovo1988.DAO.ProductRepository;
import com.gmail.morovo1988.DAO.TypeRepository;
import com.gmail.morovo1988.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BasketRepository basketRepository;



    public void addOrder(Order order) {
        orderRepository.save(order);
    }



    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Long countProduct() {
        return productRepository.count();
    }

    @Override
    public void deleteListProducts(Long[] idList) {
        for (long id : idList)
            productRepository.delete(productRepository.getOne(id));

    }

//    @Transactional(readOnly=true)
//    public List<Product> findAll(Pageable pageable) {
//        return productRepository.findAll(pageable).getContent();
//    }

//    @Transactional(readOnly = true)
//    public long count() {
//        return productRepository.count();
//    }

    @Transactional(readOnly = true)
    public long countOrder() {
        return basketRepository.count();
    }


//    @Transactional
//    public void deleteProducts(long[] idList) {
//        for (long id : idList)
//            productRepository.delete(productRepository.getOne(id));
//    }
    @Transactional
    public void deleteProduct(long idList) {

            productRepository.delete(productRepository.getOne(idList));
    }
    @Transactional
    public void deleteBasketOrder(long[] idList) {
        for (long id : idList)
            basketRepository.delete(basketRepository.getOne(id));
    }

    @Transactional
    public void orderProducts(User users, long[] idList) {
        for (long id : idList) {
            Product product = productRepository.getOne(id);
            BasketOrder basketOrder = new BasketOrder(users,product.getType(),product.getBrand(),product.getDiametr(),product.getPrice());
            basketRepository.save(basketOrder);

        }
    }
    @Transactional
    public void order(User users, long idList) {

            Product product = productRepository.getOne(idList);
            BasketOrder basketOrder = new BasketOrder(users,product.getType(),product.getBrand(),product.getDiametr(),product.getPrice());
            basketRepository.save(basketOrder);


    }

//    @Transactional
//    public void addType(Type type) {
//        typeRepository.save(type);
//    }



//    @Transactional(readOnly=true)
//    public List<Type> findTypes() {
//        return typeRepository.findAll();
//    }

//    @Transactional(readOnly = true)
//    public long countByType(Type type) {
//        return productRepository.countByType(type);
//    }

    @Transactional(readOnly=true)
    public List<Product> findProductsByType(Type type, Pageable pageable) {
        return productRepository.findByType(type, pageable);
    }

//    @Transactional(readOnly=true)
//    public Type findType(long id) {
//        return typeRepository.getOne(id);
//    }

    @Transactional(readOnly=true)
    public List<BasketOrder> findAllOrders(Pageable pageable) {
        return basketRepository.findAll(pageable).getContent();
    }
    @Transactional(readOnly=true)
    public List<BasketOrder> findByOrder(User user, Pageable pageable) {
        return basketRepository.findByOrder(user, pageable);
    }
    @Transactional(readOnly=true)
    public List<BasketOrder> findByOrder(User user) {
        return basketRepository.findByOrder(user);
    }

//    @Transactional
//    public void deleteBasketOrder(BasketOrder basketOrder) {
//        basketRepository.delete(basketOrder);
//    }

//    @Transactional
//    public void addBasketOrder(BasketOrder basketOrder) {
//        basketRepository.save(basketOrder);
//    }

    @Transactional(readOnly=true)
    public List<BasketOrder> findAllOrder(Pageable pageable) {
        return basketRepository.findAllOrder(pageable);
    }
}
