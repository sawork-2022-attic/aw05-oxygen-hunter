package com.micropos.cart.service;

import com.micropos.cart.model.Cart;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private List<Item> items = new LinkedList<>();

    @Override
    public List<Item> items() {

        return items;
    }

    @Override
    public List<Item> addItem(Item item) {
        items.add(item);
        return items;
    }

    @Override
    public List<Item> removeItem(Item item) {
        items.remove(item);
        return items;
    }
}
