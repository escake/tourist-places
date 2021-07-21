package com.touristplaces.touristplaces.service.photo;

import com.touristplaces.touristplaces.data.Photo;
import com.touristplaces.touristplaces.data.Place;
import com.touristplaces.touristplaces.data.User;
import com.touristplaces.touristplaces.dto.PhotoDto.PhotoRes;
import com.touristplaces.touristplaces.testingUtil.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.assertj.core.internal.bytebuddy.utility.RandomString.make;

class PhotoServiceGetAllMyPhotosByPlaceIdTests extends IntegrationTestBase {

    @WithMockUser("test-user")
    @Test
    void whenUserHasPhotosForPlace_thenReturnThem() {
        // given
        final User user = getTestUser();

        final Place place = placeRepository.save(new Place("Sarajevo"));
        final Photo photo1 = photoRepository.save(new Photo(make(), make(), user, place));
        final Photo photo2 = photoRepository.save(new Photo(make(), make(), user, place));

        final Place otherPlace = placeRepository.save(new Place("Mostar"));
        final Photo otherPlacePhoto = photoRepository.save(new Photo(make(), make(), user, otherPlace));

        final User otherUser = userRepository.save(new User(make(), make()));
        final Photo otherUserPhoto = photoRepository.save(new Photo(make(), make(), otherUser, place));

        // when
        final List<PhotoRes> res = photoService.getAllMyPhotosByPlaceId(place.getId());

        // then
        Assertions.assertThat(res).hasSize(2).extracting(PhotoRes::getId).containsOnly(photo1.getId(), photo2.getId());
    }
}
