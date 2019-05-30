package com.gmail.morovo1988.Entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Дом on 31.07.2017.
 */
@Entity
@Table(name="Products")
@NoArgsConstructor
@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;


    @Column(name = "Brands", nullable = false)
    private String brand;
    @Column
    private double diametr;
    @Column
    private  double price;

    public Product( String brand, double diametr, double price) {

        this.brand = brand;
        this.diametr = diametr;
        this.price = price;
    }

    public Product(Type type, String brand, double diametr, double price) {
        this.type = type;
        this.brand = brand;
        this.diametr = diametr;
        this.price = price;
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