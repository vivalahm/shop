package com.vivalahm.shop.repository;

import com.vivalahm.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByOrderByUpdatedAtDesc();
}
