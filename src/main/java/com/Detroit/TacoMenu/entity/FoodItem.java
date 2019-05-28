package com.Detroit.TacoMenu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodItem {
	@Column
    private Float itemPrice;

    @Id
    @Column(unique = true)
    private String itemName;
    
    public FoodItem(){
    	
    }

    public FoodItem(String itemName, Float itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public Float getItemPrice() {
        return itemPrice;
    }
    
	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "FoodItem [itemPrice=" + itemPrice + ", itemName=" + itemName + "]";
	}
}