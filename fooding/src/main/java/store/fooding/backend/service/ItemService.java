package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.common.exception.BadRequestException;
import store.fooding.backend.common.response.status.BaseExceptionResponseStatus;
import store.fooding.backend.dto.item.ItemRequest;
import store.fooding.backend.dto.item.ItemResponse;
import store.fooding.backend.dto.item.ItemUpdateRequest;
import store.fooding.backend.model.Item;
import store.fooding.backend.repository.ItemRepository;
import store.fooding.backend.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
    public ItemResponse registerItem(Long userId, ItemRequest request) {
        Item item = new Item();
        item.setItemName(request.getItemName());
        item.setItemDescription(request.getItemDescription());
        item.setExpirationDate(request.getExpirationDate());
        item.setItemLocation(request.getItemLocation());
        item.setQuantity(request.getQuantity());
        item.setItemStatus(request.getItemStatus()); // 상태 SHARING / EXCHANGE

        userRepository.findById(userId).ifPresent(item::setUser);

        Item saved = itemRepository.save(item);

        return new ItemResponse(
                saved.getItemId(),
                saved.getItemName(),
                saved.getItemDescription(),
                saved.getExpirationDate(),
                saved.getItemLocation(),
                saved.getQuantity(),
                saved.getItemStatus()
        );
    }

    public List<ItemResponse> getMyItems(Long userId) {
        List<Item> items = itemRepository.findByUser_UserId(userId);
        return items.stream()
                .map(item -> new ItemResponse(
                        item.getItemId(),
                        item.getItemName(),
                        item.getItemDescription(),
                        item.getExpirationDate(),
                        item.getItemLocation(),
                        item.getQuantity(),
                        item.getItemStatus()
                )).toList();
    }

    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST));
        itemRepository.delete(item);
    }

    public ItemResponse updateItem(Long itemId, ItemUpdateRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST));

        item.setItemName(request.getItemName());
        item.setItemDescription(request.getItemDescription());
        item.setExpirationDate(request.getExpirationDate());
        item.setItemLocation(request.getItemLocation());
        item.setQuantity(request.getQuantity());

        Item updated = itemRepository.save(item);

        return new ItemResponse(
                updated.getItemId(),
                updated.getItemName(),
                updated.getItemDescription(),
                updated.getExpirationDate(),
                updated.getItemLocation(),
                updated.getQuantity(),
                updated.getItemStatus()
        );
    }

}
