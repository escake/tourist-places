package com.touristplaces.touristplaces.controller;

import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.dto.PlaceDto.PlaceRes;
import com.touristplaces.touristplaces.service.PhotoService;
import com.touristplaces.touristplaces.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PhotoService photoService;

    @GetMapping
    public List<PlaceRes> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/{id}/photos")
    public List<PhotoRes> getAllPhotosByPlaceId(@PathVariable Long id) {
        return photoService.getAllByPlaceId(id);
    }

    @PostMapping("/{id}/photos")
    public void addPhoto(@PathVariable long id, @RequestParam MultipartFile image) {
        photoService.addPhoto(id, image);
    }
}
