package com.vivalahm.shop.controller;

import com.vivalahm.shop.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("items", itemService.getItems());
        return "item/list";
    }

    @GetMapping("/Item/{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("item", itemService.getItem(id));
        return "item/detail";
    }

    @GetMapping("/write")
    public String write(){
        return "item/write";
    }

    @PostMapping("/addItem")
    public String addItem(String title, Long price, String description){
        itemService.addItem(title, price, description);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id){
        model.addAttribute("item", itemService.getItem(id));
        return "item/update";
    }

    @PutMapping("/updateItem/{id}")
    public String updateItem(@PathVariable Long id, String title, Long price, String description){
        itemService.updateItem(id, title, price, description);
        return "redirect:/Item/{id}";
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("success");
    }


}
