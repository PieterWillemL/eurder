package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.InvalidAmountException;
import com.switchfully.eurder.exceptions.InvalidItemNameException;
import com.switchfully.eurder.exceptions.InvalidPriceException;

import java.util.Objects;

public class Item {

    private String name;
    private Double price;
    private Integer amount;
    private String description;

    public Item(String name, Double price, Integer amount, String description) {
        validateInput(name, price, amount);
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    private void validateInput(String name, Double price, Integer amount){
        if(name == null || name.isEmpty()){
            throw new InvalidItemNameException();
        }
        if(price == null || price < 0){
            throw new InvalidPriceException();
        }
        if(amount == null || amount < 0){
            throw new InvalidAmountException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(price, item.price) && Objects.equals(amount, item.amount) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount, description);
    }

    @Override
    public String toString() {
        return " - Item - \nname: " + name + "\nprice: " + price + "\namount: " + amount + "\ndescription: " + description;
    }
}
