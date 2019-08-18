package org.devon.app.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Builder
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer constructionYear;
    //in progress, built etc.
//    String constructionState;
    String resistanceStructure;
    String buildingType;
    Integer floor;
    Integer totalNoOfFloors;

    @OneToOne(mappedBy = "construction")
    Estate estate;

}
