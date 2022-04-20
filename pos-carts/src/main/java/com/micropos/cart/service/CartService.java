package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;

import java.util.List;

public interface CartService {

    public List<Item> items();

//    public Cart add(Cart cart, String productId, int amount);
//
//    public Cart modify(Cart cart, String productId, int amount);

    public List<Item> addItem(Item item);

    public List<Item> removeItem(Item item);
}
