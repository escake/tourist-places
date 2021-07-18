package com.touristplaces.touristplaces.dto;

import com.touristplaces.touristplaces.data.Place;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlaceDto {

    @Data
    public static class PlaceRes {
        long id;
        String name;

        public PlaceRes(Place place) {
            this.id = place.getId();
            this.name = place.getName();
        }
    }
}
