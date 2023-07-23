package edu.school21.models;

import java.util.Objects;

public class Product {
    private final Long id;
    private String name;
    private Integer price;

    public Product(Long id, String name, Integer price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass() ) return false;

        Product product = (Product) obj;
        return id.equals(product.getId()) && name.equals(product.getName()) && price.equals(product.getPrice());
    }

    @Override
    public String toString() {
        return "Product { " +
                "id = " + id +
                ", name = " + name +
                ", price = " + price +
                " }";
    }

    public Integer getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
