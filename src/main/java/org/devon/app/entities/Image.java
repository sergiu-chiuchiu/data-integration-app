package org.devon.app.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String imageName;

    @ManyToOne
    AdvertisementPage advertisementPage;

}

