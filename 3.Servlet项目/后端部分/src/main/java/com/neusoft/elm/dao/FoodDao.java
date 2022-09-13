package com.neusoft.elm.dao;

import com.neusoft.elm.po.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> listFoodByBusinessId(Integer businessId) throws Exception;
}
