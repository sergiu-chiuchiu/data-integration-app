package org.devon.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.Partitioning;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    String state;
    String propertyType;
    String city;
    String neighbourhood;

    Area area;
    Construction construction;
    Rooms rooms;

}
