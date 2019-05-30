package com.gmail.morovo1988.Entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Дом on 31.07.2017.
 */
@Entity
@Table(name="Orders")
@NoArgsConstructor
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue
    private long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<BasketOrder> basketsOrders = new ArrayList<BasketOrder>();

    public void addBasketOrders(BasketOrder a){
        a.setOrder(this);
        basketsOrders.add(a);
    }


    public Order(Date date) {
        this.date = date;

    }

    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BasketOrder> getBasketsOrders() {
        return basketsOrders;
    }

    public void setBasketsOrders(List<BasketOrder> basketsOrders) {
        this.basketsOrders = basketsOrders;
    }
}