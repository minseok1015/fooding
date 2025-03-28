package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.model.Item;
import store.fooding.backend.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
}
