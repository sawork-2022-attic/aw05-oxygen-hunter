package com.micropos.cart.mapper;

import com.micropos.cart.dto.ItemDto;
import com.micropos.cart.model.Item;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface CartMapper {

    Collection<ItemDto> toItemsDto(Collection<Item> items);

    Collection<Item> toItems(Collection<ItemDto> items);

    Item toItem(ItemDto itemDto);

    ItemDto toItemDto(Item item);
}
