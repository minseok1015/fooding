package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.model.Sharing;
import store.fooding.backend.repository.SharingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingService {

    private final SharingRepository sharingRepository;

    public List<Sharing> getAllSharings() {
        return sharingRepository.findAll();
    }
}
