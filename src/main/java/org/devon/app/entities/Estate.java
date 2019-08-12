package org.devon.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.Partitioning;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Partitioning partitioning;
    Integer floor;
    ComfortType comfortType;
    String estateState;
    String propertyType;
    String city;
    String neighbourhood;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Area area;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Construction construction;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Rooms rooms;

    @OneToOne(mappedBy ="estate")
    AdvertisementPage advertisementPage;

}
