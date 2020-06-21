package org.devon.app.entities;

import lombok.*;
import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.Partitioning;

import javax.persistence.*;

//@NoArgsConstructor
@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder
@ToString
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Partitioning partitioning;
    ComfortType comfortType;
    String estateState;
    String propertyType;
    String region;
    String neighbourhood;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Area area;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Construction construction;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Rooms rooms;

}
