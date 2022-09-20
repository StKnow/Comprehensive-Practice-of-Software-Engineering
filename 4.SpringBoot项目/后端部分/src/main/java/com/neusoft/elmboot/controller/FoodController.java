package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.service.BusinessService;
import com.neusoft.elmboot.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/FoodController")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("/listFoodByBusinessId")
    public List<Food> listFoodByBusinessId(Food food) throws Exception {
        return foodService.listFoodByBusinessId(food.getBusinessId());
    }
}
