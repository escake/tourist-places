package com.touristplaces.touristplaces.service;

import com.touristplaces.touristplaces.dto.PlaceDto.PlaceRes;
import com.touristplaces.touristplaces.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public List<PlaceRes> getAll() {
        return placeRepository.findAll().stream().map(PlaceRes::new).collect(toUnmodifiableList());
    }
}
