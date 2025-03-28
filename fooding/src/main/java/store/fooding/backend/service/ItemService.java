package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import store.fooding.backend.common.exception.BadRequestException;
import store.fooding.backend.common.response.status.BaseExceptionResponseStatus;
import store.fooding.backend.dto.item.ItemRequest;
import store.fooding.backend.dto.item.ItemResponse;
import store.fooding.backend.dto.item.ItemUpdateRequest;
import store.fooding.backend.model.Category;
import store.fooding.backend.model.Item;
import store.fooding.backend.model.Restaurant;
import store.fooding.backend.model.User;
import store.fooding.backend.repository.CategoryRepository;
import store.fooding.backend.repository.ItemRepository;
import store.fooding.backend.repository.RestaurantRepository;
import store.fooding.backend.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static store.fooding.backend.common.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 전체 상품 조회
     */
    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();

        return items.stream().map(item -> {
            String registeredBy = "";
            String phoneNumber = "";
            if ("user".equals(item.getActorType())) {
                Optional<User> user = userRepository.findById(item.getActorId());
                registeredBy = user.map(User::getUserName).orElse("알 수 없음");
                phoneNumber = user.map(User::getPhoneNumber).orElse("");
            } else if ("restaurant".equals(item.getActorType())) {
                Optional<Restaurant> restaurant = restaurantRepository.findById(item.getActorId());
                registeredBy = restaurant.map(Restaurant::getRestaurantName).orElse("알 수 없음");
            }

            return ItemResponse.from(item, registeredBy, phoneNumber);
        }).collect(Collectors.toList());
    }

    /**
     * 내 상품 조회
     */
    public List<ItemResponse> getMyItems(Long userId) {
        // 내 상품만 조회
        List<Item> items = itemRepository.findByActorTypeAndActorId("user", userId);

        // 등록자의 정보 (내 정보)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));

        return items.stream()
                .map(item -> ItemResponse.from(item, user.getUserName(), user.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    /**
     * 상품 등록
     */
    public ItemResponse registerItem(Long userId, ItemRequest request, MultipartFile thumbnailImage) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST));

        Item item = new Item();
        item.setItemName(request.getItemName());
        item.setItemDescription(request.getItemDescription());
        item.setExpirationDate(LocalDate.parse(request.getExpirationDate()));
        item.setItemLocation(request.getItemLocation());
        item.setQuantity(request.getQuantity());
        item.setItemStatus(request.getItemStatus());
        item.setActorType("user");
        item.setActorId(userId);
        item.setCategory(category);

        if (thumbnailImage != null && !thumbnailImage.isEmpty()) {
            try {
                item.setThumbnailImage(thumbnailImage.getBytes());
            } catch (IOException e) {
                throw new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST);
            }
        }

        Item saved = itemRepository.save(item);

        return ItemResponse.from(saved, null, null);
    }


    /**
     * 상품 삭제
     */
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST));
        itemRepository.delete(item);
    }

    /**
     * 상품 수정
     */
    public ItemResponse updateItem(Long itemId, ItemUpdateRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.BAD_REQUEST));

        item.setItemName(request.getItemName());
        item.setItemDescription(request.getItemDescription());
        item.setExpirationDate(request.getExpirationDate());
        item.setItemLocation(request.getItemLocation());
        item.setQuantity(request.getQuantity());
        item.setItemStatus(request.getItemStatus());

        Item updated = itemRepository.save(item);

        String registeredBy = "";
        String phoneNumber = "";
        if ("user".equals(updated.getActorType())) {
            Optional<User> user = userRepository.findById(updated.getActorId());
            registeredBy = user.map(User::getUserName).orElse("알 수 없음");
            phoneNumber = user.map(User::getPhoneNumber).orElse("");
        } else if ("restaurant".equals(updated.getActorType())) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(updated.getActorId());
            registeredBy = restaurant.map(Restaurant::getRestaurantName).orElse("알 수 없음");
        }

        return ItemResponse.from(updated, registeredBy, phoneNumber);
    }

    /**
     * 상품 검색
     */
    public List<ItemResponse> searchItems(String keyword) {
        List<Item> items = itemRepository.searchItemsByKeyword(keyword);

        return items.stream().map(item -> {
            String registeredBy = "";
            String phoneNumber = "";
            if ("user".equals(item.getActorType())) {
                Optional<User> user = userRepository.findById(item.getActorId());
                registeredBy = user.map(User::getUserName).orElse("알 수 없음");
                phoneNumber = user.map(User::getPhoneNumber).orElse("");
            } else if ("restaurant".equals(item.getActorType())) {
                Optional<Restaurant> restaurant = restaurantRepository.findById(item.getActorId());
                registeredBy = restaurant.map(Restaurant::getRestaurantName).orElse("알 수 없음");
            }

            return ItemResponse.from(item, registeredBy, phoneNumber);
        }).collect(Collectors.toList());
    }

}
