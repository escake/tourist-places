package com.touristplaces.touristplaces.service;

import com.touristplaces.touristplaces.data.Photo;
import com.touristplaces.touristplaces.data.Place;
import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.repository.PhotoRepository;
import com.touristplaces.touristplaces.repository.PlaceRepository;
import com.touristplaces.touristplaces.service.ImageStore.ImageStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;
    private final ImageStorageService imageStorageService;

    @Transactional
    public void addPhoto(long placeId, MultipartFile image) {
        final Place place = placeRepository.getById(placeId);

        final String name = image.getOriginalFilename();
        final String path = imageStorageService.storeAndReturnPath(image);

        System.out.println(name);
        System.out.println(path);
        System.out.println("---");
        final Photo photo = new Photo(name, path, place);

        place.addPhoto(photo);
        photoRepository.save(photo);
    }

    @Transactional(readOnly = true)
    public List<PhotoRes> getAllByPlaceId(long placeId) {
        return photoRepository.getAllByPlaceId(placeId).stream().map(PhotoRes::new).collect(toUnmodifiableList());
    }
}
