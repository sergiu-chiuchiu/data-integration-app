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
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer constructionYear;
    String constructionState;
    String resistanceStructure;
    String buildingType;
    Integer floor;
    Integer totalNoOfFloors;

    @OneToOne(mappedBy = "construction")
    Estate estate;

}
