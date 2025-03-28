package store.fooding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import store.fooding.backend.common.response.SuccessResponse;
import store.fooding.backend.dto.item.ItemRequest;
import store.fooding.backend.dto.item.ItemResponse;
import store.fooding.backend.dto.item.ItemUpdateRequest;
import store.fooding.backend.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemResponse> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/register")
    public ItemResponse registerItem(
            @RequestParam Long userId,
            @RequestPart ItemRequest request,
            @RequestPart(required = false) MultipartFile thumbnailImage
    ) {
        return itemService.registerItem(userId, request, thumbnailImage);
    }


    @GetMapping("/my")
    public List<ItemResponse> getMyItems(@RequestParam Long userId) {
        return itemService.getMyItems(userId);
    }

    @DeleteMapping("/{itemId}")
    public SuccessResponse deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return new SuccessResponse("삭제 완료");
    }

    @PutMapping("/{itemId}")
    public ItemResponse updateItem(
            @PathVariable Long itemId,
            @RequestBody ItemUpdateRequest request
    ) {
        return itemService.updateItem(itemId, request);
    }

    @GetMapping("/search")
    public List<ItemResponse> searchItems(@RequestParam String keyword) {
        return itemService.searchItems(keyword);
    }

}
