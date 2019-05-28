package com.Detroit.TacoMenu.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Detroit.TacoMenu.entity.FoodItem;
import com.Detroit.TacoMenu.repository.FoodItemRepository;
import com.Detroit.TacoMenu.restEntity.OrderItem;
import com.Detroit.TacoMenu.service.TacoService;
import com.Detroit.TacoMenu.service.TacoServiceImpl;

@RunWith(SpringRunner.class)
public class TacoServiceImplTest {

    @TestConfiguration
    static class TacoServiceImplTestConfiguration {

        @Bean
        public TacoService getService() {
            return new TacoServiceImpl();
        }
    }

    @Autowired
    private TacoService service;

    @MockBean
    private FoodItemRepository repository;

    private List<FoodItem> foodItems;

    @Before
    public void setup() {
    	FoodItem foodItem1 = new FoodItem();
    	foodItem1.setItemName ("Veggie Taco");
    	foodItem1.setItemPrice(2.5f);
    	
    	FoodItem foodItem2 = new FoodItem();
    	foodItem2.setItemName ("Chicken Taco");
    	foodItem2.setItemPrice(3f);
    	
    	FoodItem foodItem3 = new FoodItem();
    	foodItem3.setItemName ("Beef Taco");
    	foodItem3.setItemPrice(3f);
    	
    	FoodItem foodItem4 = new FoodItem();
    	foodItem4.setItemName ("Chorizo Taco");
    	foodItem4.setItemPrice(3.5f);
    	
    	foodItems = new ArrayList<FoodItem>();
    	
    	foodItems.add(foodItem1);
    	foodItems.add(foodItem2);
    	foodItems.add(foodItem3);
    	foodItems.add(foodItem4);

        Mockito.when(repository.findAll())
               .thenReturn(foodItems);

        Mockito.when(repository.findById(Mockito.anyString()))
        .thenAnswer(i -> Optional.of(foodItems.stream()
        		.filter(item -> i.getArguments()[0].equals(item.getItemName()))
        		.findAny()
        		.orElse(null)));
        
        Mockito.when(repository.findByItemName(Mockito.anyString()))
        .thenAnswer(i -> Optional.of(foodItems.stream()
        		.filter(item -> i.getArguments()[0].equals(item.getItemName()))
        		.findAny()
        		.orElse(null)));
    }

    @Test
    public void findAll() throws Exception {
        List<FoodItem> result = service.findAll();
        Assert.assertEquals("foodItems list should match", foodItems, result);
    }

    @Test
    public void findOne() throws Exception {
    	FoodItem result = service.findOne(foodItems.get(0)
                                                   .getItemName());
        Assert.assertEquals("foodItem should match", foodItems.get(0), result);
    }
    
    @Test
	public void orderTotalWithoutDiscountTest(){
		OrderItem order = new OrderItem();
		order.setItemName("Veggie Taco");
		order.setItemCount(2);
		List<OrderItem> list = new ArrayList<OrderItem>();
		list.add(order);
		
		float orderTotal = service.calculateOrder(list);
		assertEquals(5.0F, orderTotal, 0.02F);
	}
    
    @Test(expected = NullPointerException.class)
	public void orderWithInvalidItem(){
		OrderItem order = new OrderItem();
		order.setItemName("Paneer Taco");
		order.setItemCount(2);
		List<OrderItem> list = new ArrayList<OrderItem>();
		list.add(order);
		
		System.out.println("********************************************");
		float total = service.calculateOrder(list);
		System.out.println(total+"********************************************");
	}
	
	@Test
	public void orderTotalWithDiscountTest(){
		OrderItem order1 = new OrderItem();
		order1.setItemName("Veggie Taco");
		order1.setItemCount(4);
		
		OrderItem order2 = new OrderItem();
		order2.setItemName("Chicken Taco");
		order2.setItemCount(2);
		
		List<OrderItem> list = new ArrayList<OrderItem>();
		list.add(order1);
		list.add(order2);
		
		float orderTotal = service.calculateOrder(list);
		assertEquals(12.8F, orderTotal, 0.02F);
	}
}