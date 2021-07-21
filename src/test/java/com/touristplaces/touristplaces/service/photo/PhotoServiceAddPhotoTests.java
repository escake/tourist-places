package com.touristplaces.touristplaces.service.photo;

import com.touristplaces.touristplaces.data.Photo;
import com.touristplaces.touristplaces.data.Place;
import com.touristplaces.touristplaces.testingUtil.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PhotoServiceAddPhotoTests extends IntegrationTestBase {

    final String IMAGE_NAME = "image1.png";

    @WithMockUser(username = "test-user")
    @Test
    void whenGivenValidPhoto_thenAddIt() {
        // given
        final Place place = placeRepository.save(new Place("Sarajevo"));
        final MultipartFile image = testUtil.getFile(IMAGE_NAME);

        // when
        photoService.addPhoto(place.getId(), image);

        // then
        final List<Photo> allPhotos = photoRepository.findAll();
        assertThat(allPhotos).hasSize(1);
        assertThat(place.getPhotos()).hasSize(1);
        assertThat(allPhotos.get(0)).isEqualTo(place.getPhotos().get(0));
        assertThat(allPhotos.get(0).getPlace().getId()).isEqualTo(place.getId());
        assertThat(allPhotos.get(0).getUser().getUsername()).isEqualTo("test-user");
    }
}
