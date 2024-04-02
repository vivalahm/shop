package com.vivalahm.shop.service;

import com.vivalahm.shop.entity.Item;
import com.vivalahm.shop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItems(){
        return itemRepository.findAllByOrderByIdAsc();
    }

    public void addItem(String title, Long price, String description){
        Item item = new Item();
        if(title == null || title.length()>255){
            throw new IllegalArgumentException("title is invalid");
        }
        item.setTitle(title);
        if(price == null || price < 0){
            throw new IllegalArgumentException("price is invalid");
        }
        item.setPrice(price);
        if(description == null || description.length()>255){
            throw new IllegalArgumentException("description is invalid");
        }
        item.setDescription(description);
        itemRepository.save(item);
    }

    public Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id is invalid"));
    }

    public void updateItem(Long id, String title, Long price, String description){
        Item item = itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id is invalid"));
        if(title == null || title.length()>255){
            throw new IllegalArgumentException("title is invalid");
        }
        item.setTitle(title);
        if(price == null || price < 0){
            throw new IllegalArgumentException("price is invalid");
        }
        item.setPrice(price);
        if(description == null || description.length()>255){
            throw new IllegalArgumentException("description is invalid");
        }
        item.setDescription(description);
        itemRepository.save(item);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

}
