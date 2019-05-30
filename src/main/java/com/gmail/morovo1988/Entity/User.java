package com.gmail.morovo1988.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;
    private String phone;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Order>  orders = new ArrayList<Order>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<BasketOrder>  basketOrders = new ArrayList<BasketOrder>();

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, UserRole role, String email, String phone) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }
    public void addOrder(Order a){
        a.setUser(this);
        orders.add(a);
    }

    public User() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<BasketOrder> getBasketOrders() {
        return basketOrders;
    }

    public void setBasketOrders(List<BasketOrder> basketOrders) {
        this.basketOrders = basketOrders;
    }
}
