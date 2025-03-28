package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.common.exception.BadRequestException;
import store.fooding.backend.common.response.status.BaseExceptionResponseStatus;
import store.fooding.backend.dto.user.*;
import store.fooding.backend.model.Item;
import store.fooding.backend.model.User;
import store.fooding.backend.repository.ItemRepository;
import store.fooding.backend.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static store.fooding.backend.common.response.status.BaseExceptionResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public UserResponse registerUser(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(DUPLICATE_EMAIL);
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setUserPassword(request.getUserPassword());
        user.setLocation(request.getLocation());
        user.setCreateAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getUserId(),
                saved.getUserName(),
                saved.getEmail(),
                saved.getLocation(),
                saved.getPhoneNumber()
        );    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(EMAIL_NOT_FOUND));

        if (!user.getUserPassword().equals(request.getUserPassword())) {
            throw new BadRequestException(PASSWORD_NO_MATCH);
        }

        return new UserResponse(user.getUserId(), user.getUserName(), user.getEmail(), user.getLocation(), user.getPhoneNumber());
    }

    public UserNameResponse getUserNameById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.USER_NOT_FOUND));
        return new UserNameResponse(user.getUserId(), user.getUserName());
    }

    public MyPageResponse getMyPageInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.EMAIL_NOT_FOUND));

        List<Item> items = itemRepository.findByActorTypeAndActorId("user", userId);

        List<MyPageResponse.ItemInfo> itemInfos = items.stream()
                .map(item -> new MyPageResponse.ItemInfo(
                        item.getItemId(),
                        item.getItemName(),
                        item.getQuantity(),
                        item.getExpirationDate(),
                        item.getItemDescription()
                )).collect(Collectors.toList());

        MyPageResponse.UserInfo userInfo = new MyPageResponse.UserInfo(
                user.getUserId(),
                user.getUserName(),
                user.getPhoneNumber(),
                user.getLocation()
        );

        return new MyPageResponse(userInfo, itemInfos);
    }





}
