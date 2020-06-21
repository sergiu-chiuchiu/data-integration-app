package org.devon.app.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder
@ToString
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

}
