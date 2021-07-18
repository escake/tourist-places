package com.touristplaces.touristplaces.data;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Photo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @EqualsAndHashCode.Include
    @NonNull
    private String path;

    @NonNull
    @Setter
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;


}
