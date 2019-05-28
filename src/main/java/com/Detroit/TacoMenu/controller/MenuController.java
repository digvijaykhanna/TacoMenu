package com.Detroit.TacoMenu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Detroit.TacoMenu.entity.FoodItem;
import com.Detroit.TacoMenu.restEntity.OrderItem;
import com.Detroit.TacoMenu.service.TacoService;

@RestController
public class MenuController {

    @Autowired
    TacoService service;

    @RequestMapping(value= "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Float calculateOrder(@RequestBody List<OrderItem> orderitems) {
        return service.calculateOrder(orderitems);
    }
    
    @RequestMapping(value = "/items", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<FoodItem> listAllItems(){
    	return service.findAll();
    }
}