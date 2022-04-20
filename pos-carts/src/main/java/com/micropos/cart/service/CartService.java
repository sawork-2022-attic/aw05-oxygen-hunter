package com.micropos.cart.service;

import com.micropos.cart.model.Item;

import java.util.List;

public interface CartService {

    List<Item> items();

    List<Item> addItem(Item item);

    boolean removeItem(String productId);
}
