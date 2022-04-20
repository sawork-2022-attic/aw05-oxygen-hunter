package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;

import java.util.List;

public interface CartService {

    public Cart add(Cart cart, String productId, int amount);

    public List<Item> items();

    public Cart modify(Cart cart, String productId, int amount);
}
