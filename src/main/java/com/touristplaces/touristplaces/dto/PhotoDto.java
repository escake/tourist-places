package com.touristplaces.touristplaces.dto;

import com.touristplaces.touristplaces.data.Photo;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PhotoDto {

    @Data
    public static class PhotoRes {
        long id;
        String name;
        String path;

        public PhotoRes(Photo photo) {
            this.id = photo.getId();
            this.name = photo.getName();
            this.path = photo.getPath();
        }
    }
}
