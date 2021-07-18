package com.touristplaces.touristplaces.repository;

import com.touristplaces.touristplaces.data.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
