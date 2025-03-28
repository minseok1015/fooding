package store.fooding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.fooding.backend.model.Sharing;
import store.fooding.backend.service.SharingService;

import java.util.List;

@RestController
@RequestMapping("/sharing")
@RequiredArgsConstructor
public class SharingController {

    private final SharingService sharingService;

    @GetMapping
    public List<Sharing> getSharings() {
        return sharingService.getAllSharings();
    }
}
