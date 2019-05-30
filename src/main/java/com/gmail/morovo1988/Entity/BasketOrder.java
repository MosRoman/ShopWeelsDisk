package com.gmail.morovo1988.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Basket")
@NoArgsConstructor
@Getter
@Setter
public class BasketOrder {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;


    @Column(name = "Brands", nullable = false)
    private String brand;
    @Column
    private double diametr;

    @Column
    private  double price;

    public BasketOrder(User user, Type type, String brand, double diametr, double price) {
        this.user = user;
        this.type = type;
        this.brand = brand;
        this.diametr = diametr;
        this.price = price;
    }

    public BasketOrder(User user, Order order, Type type, String brand, double diametr, double price) {
        this.user = user;
        this.order = order;
        this.type = type;
        this.brand = brand;
        this.diametr = diametr;

        this.price = price;
    }

    public BasketOrder(Order order, String brand, double diametr, int amount, double price) {
        this.order = order;
        this.brand = brand;
        this.diametr = diametr;

        this.price = price;
    }

    public BasketOrder(Type type, String brand, double diametr, double price) {
        this.type = type;
        this.brand = brand;
        this.diametr = diametr;
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getDiametr() {
        return diametr;
    }

    public void setDiametr(double diametr) {
        this.diametr = diametr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
