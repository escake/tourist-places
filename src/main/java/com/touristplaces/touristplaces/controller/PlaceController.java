package com.touristplaces.touristplaces.controller;

import com.touristplaces.touristplaces.dto.PlaceDto.PlaceRes;
import com.touristplaces.touristplaces.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public List<PlaceRes> getAll() {
        return placeService.getAll();
    }
}
