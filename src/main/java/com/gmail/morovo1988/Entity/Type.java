package com.gmail.morovo1988.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Types")
@NoArgsConstructor
@Getter
@Setter
public class Type {

        @Id
        @GeneratedValue
        private long id;
        private String name;

        @OneToMany(mappedBy="type", cascade=CascadeType.ALL)
        private List<Product> products = new ArrayList<Product>();

        @OneToMany(mappedBy="type", cascade=CascadeType.ALL)
        private List<BasketOrder> basketOrders = new ArrayList<BasketOrder>();

        public Type(String name) {
            this.name = name;
        }




}
