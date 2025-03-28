package store.fooding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.fooding.backend.model.Item;
import store.fooding.backend.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }
}
