package com.touristplaces.touristplaces.service.photo;

import com.touristplaces.touristplaces.data.Photo;
import com.touristplaces.touristplaces.data.Place;
import com.touristplaces.touristplaces.data.User;
import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.testingUtil.IntegrationTestBase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.utility.RandomString.make;

class PhotoServiceGetAllByPlaceIdTests extends IntegrationTestBase {

    @Test
    void whenPlaceHasPhotos_thenReturnThem() {
        // given
        final User user = userRepository.save(new User(make(), make()));
        final Place place = placeRepository.save(new Place("Sarajevo"));
        final Photo photo1 = photoRepository.save(new Photo(make(), make(), user, place));
        final Photo photo2 = photoRepository.save(new Photo(make(), make(), user, place));

        final Place otherPlace = placeRepository.save(new Place("Mostar"));
        final Photo otherPlacePhoto = photoRepository.save(new Photo(make(), make(), user, otherPlace));

        // when
        final List<PhotoRes> res = photoService.getAllByPlaceId(place.getId());

        // then
        assertThat(res).hasSize(2).extracting(PhotoRes::getId).containsOnly(photo1.getId(), photo2.getId());
    }
}
