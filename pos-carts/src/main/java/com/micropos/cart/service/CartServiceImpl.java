package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart add(Cart cart, String productId, int amount) {
        cart.addItem(new Item(productId, amount));
        return cart;
    }

    @Override
    public Cart modify(Cart cart, String productId, int amount) {

        return cart;
    }

    @Override
    public List<Item> items() {

        return null;
    }

}
