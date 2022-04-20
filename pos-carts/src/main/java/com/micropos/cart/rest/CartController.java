package com.micropos.cart.rest;

import com.micropos.cart.dto.ItemDto;
import com.micropos.cart.api.CartApi;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.model.Item;
import com.micropos.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class CartController implements CartApi {

    private final CartMapper cartMapper;

    private final CartService cartService;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<List<ItemDto>> listCart() {
        List<ItemDto> items = new ArrayList<>((cartMapper.toItemsDto(cartService.items())));
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ItemDto>> addItem(ItemDto itemDto) {
        Item item = cartMapper.toItem(itemDto);
        List<ItemDto> items = new ArrayList<>(cartMapper.toItemsDto(cartService.addItem(item)));
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
