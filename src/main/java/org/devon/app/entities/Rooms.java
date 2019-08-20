package org.devon.app.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Builder
@ToString
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer noOfRooms;
    Integer noOfKitchens;
    Integer noOfBalconies;
    Integer noOfBathrooms;

}
