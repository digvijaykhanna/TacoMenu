package com.Detroit.TacoMenu.service;

import java.util.List;

import com.Detroit.TacoMenu.entity.FoodItem;
import com.Detroit.TacoMenu.restEntity.OrderItem;

public interface TacoService {
	Float calculateOrder(List<OrderItem> item);

	List<FoodItem> findAll();
	
	FoodItem findOne(String item);
}