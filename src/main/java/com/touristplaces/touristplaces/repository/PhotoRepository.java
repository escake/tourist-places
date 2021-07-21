package com.touristplaces.touristplaces.repository;

import com.touristplaces.touristplaces.data.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> getAllByPlaceId(long placeId);

    List<Photo> getAllByPlaceIdAndUserId(long placeId, long userId);
}
