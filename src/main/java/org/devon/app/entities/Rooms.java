package org.devon.app.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Rooms {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer noOfRooms;
    Integer noOfKitchens;
    Integer noOfBalconies;
    Integer noOfBathrooms;

    @OneToOne(mappedBy = "rooms")
    Estate estate;

}
