package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Food;

import java.util.List;

public interface FoodService {
    public List<Food> listFoodByBusinessId(Integer businessId);
}
