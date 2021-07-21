package com.touristplaces.touristplaces.service;

import com.touristplaces.touristplaces.data.Photo;
import com.touristplaces.touristplaces.data.Place;
import com.touristplaces.touristplaces.data.User;
import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.repository.PhotoRepository;
import com.touristplaces.touristplaces.repository.PlaceRepository;
import com.touristplaces.touristplaces.service.ImageStore.ImageStorageService;
import com.touristplaces.touristplaces.service.auth.AuthService;
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
    private final AuthService authService;

    @Transactional
    public void addPhoto(long placeId, MultipartFile image) {
        final User currentUser = authService.getCurrentUser();
        final Place place = placeRepository.getById(placeId);

        final String name = image.getOriginalFilename();
        final String path = imageStorageService.storeAndReturnPath(image);

        final Photo photo = new Photo(name, path, currentUser, place);

        place.addPhoto(photo);
        photoRepository.save(photo);
    }

    @Transactional(readOnly = true)
    public List<PhotoRes> getAllByPlaceId(long placeId) {
        return photoRepository.getAllByPlaceId(placeId).stream().map(PhotoRes::new).collect(toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public List<PhotoRes> getAllMyPhotosByPlaceId(long placeId) {
        final User currentUser = authService.getCurrentUser();
        return photoRepository.getAllByPlaceIdAndUserId(placeId, currentUser.getId()).stream().map(PhotoRes::new).collect(toUnmodifiableList());
    }
}
