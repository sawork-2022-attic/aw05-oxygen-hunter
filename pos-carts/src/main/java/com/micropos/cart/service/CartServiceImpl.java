package com.micropos.cart.service;

import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private static final String PRODUCT = "http://product-service/api/products";
    private static final ParameterizedTypeReference<List<Product>> type = new ParameterizedTypeReference<>() {
    };
    private static final List<Product> fallback = new ArrayList<>();
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory<?, ?> factory;
    private final List<Item> items = new LinkedList<>();

    static {
        fallback.add(new Product("13284888", "Java从入门到精通（第6版）（软件开发视频大讲堂） 4.21-4.23百万图书嗨购3天,满减叠券享600减400(具体优惠以商品详情页为准)团购电话4006186622抢购",
                75.8, "https://img13.360buyimg.com/n1/s200x200_jfs/t1/186038/9/7947/120952/60bdd993E41eea7e2/48ab930455d7381b.jpg"));
        fallback.add(new Product("13543582", "Java高并发与集合框架：JCF和JUC源码分析与实现(博文视点出品) 掌握Java集合框架和Java并发工具包",
                117.8, "https://img12.360buyimg.com/n1/s200x200_jfs/t1/208611/22/12064/282488/61b22ce7E08894c60/be5539046b79c2d4.jpg"));
    }

    @Autowired
    public CartServiceImpl(RestTemplate restTemplate, CircuitBreakerFactory<?, ?> factory) {
        this.restTemplate = restTemplate;
        this.factory = factory;
    }

    private List<Product> getProducts(){
        CircuitBreaker cb = factory.create("product");
        return cb.run(() -> restTemplate.exchange(PRODUCT, HttpMethod.GET, null, type).getBody(), t -> fallback);
    }

    @Override
    public List<Item> items() {
        return items;
    }

    @Override
    public List<Item> addItem(Item item) {
        if (getProducts().contains(item.getProduct())) {
            items.add(item);
        }
        return items;
    }

    @Override
    public boolean removeItem(String productId) {
        return items.removeIf(item -> item.getProduct().getId().equals(productId));
    }
}
