package com.touristplaces.touristplaces.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Place {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NonNull
    @EqualsAndHashCode.Include
    private String name;

    @OneToMany(mappedBy = "place", fetch = LAZY, cascade = ALL)
    private List<Photo> photos;

    public void addPhoto(Photo photo) {
        photo.setPlace(this);
        this.getPhotos().add(photo);
    }
}
