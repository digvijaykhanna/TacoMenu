package com.Detroit.TacoMenu.repository;

import org.springframework.data.repository.CrudRepository;
import com.Detroit.TacoMenu.entity.FoodItem;
import java.util.Optional;

public interface FoodItemRepository extends CrudRepository<FoodItem, String> {
	Optional<FoodItem> findByItemName(String itemName);
}
