package com.Detroit.TacoMenu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Detroit.TacoMenu.entity.FoodItem;
import com.Detroit.TacoMenu.exception.ResourceNotFoundException;
import com.Detroit.TacoMenu.repository.FoodItemRepository;
import com.Detroit.TacoMenu.restEntity.OrderItem;

@Service
public class TacoServiceImpl implements TacoService {
	@Autowired
    FoodItemRepository repository;

    @Transactional(readOnly = true)
    public List<FoodItem> findAll() {
        return (List<FoodItem>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public FoodItem findOne(String id) {
        Optional<FoodItem> existing = repository.findByItemName(id);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("Food Item with id " + id + " doesn't exist.");
        }
        return existing.get();
    }
    
	@Override
	public Float calculateOrder(List<OrderItem> items) {
		float totalPrice = 0.0F;
		int totalItems = 0;
		for(OrderItem item: items) {
			FoodItem foodObj = findOne(item.getItemName());
			totalItems += item.getItemCount();
			totalPrice += foodObj.getItemPrice() * item.getItemCount();
		}
		
		if(totalItems >= 4) {
			float discount = totalPrice * 0.20F;
			totalPrice -= discount;
		}
		
		return totalPrice;
	}
}
