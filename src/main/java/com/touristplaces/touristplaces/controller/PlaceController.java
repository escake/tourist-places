package com.touristplaces.touristplaces.controller;

import com.touristplaces.touristplaces.dto.PhotoDto.OwnedBy;
import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.dto.PlaceDto.PlaceRes;
import com.touristplaces.touristplaces.service.PhotoService;
import com.touristplaces.touristplaces.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.touristplaces.touristplaces.dto.PhotoDto.OwnedBy.ALL;

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
    public List<PhotoRes> getAllPhotosByPlaceId(@PathVariable Long id, @RequestParam(required = false, defaultValue = "ALL") OwnedBy owner) {
        if (owner == ALL)
            return photoService.getAllByPlaceId(id);
        return photoService.getAllMyPhotosByPlaceId(id);
    }

    @PostMapping("/{id}/photos")
    public void addPhoto(@PathVariable long id, @RequestParam MultipartFile image) {
        photoService.addPhoto(id, image);
    }
}
