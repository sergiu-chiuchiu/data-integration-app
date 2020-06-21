package org.devon.app.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder
@ToString
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String imageName;

    @ManyToOne
    @ToString.Exclude
    AdvertisementPage advertisementPage;

}

