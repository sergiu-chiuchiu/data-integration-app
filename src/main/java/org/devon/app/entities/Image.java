package org.devon.app.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Builder
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String imageName;

    @ManyToOne
    AdvertisementPage advertisementPage;

}

