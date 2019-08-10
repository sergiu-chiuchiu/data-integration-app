package org.devon.app.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Area {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer usefulArea;
    Integer builtSurface;
    Integer totalUsefulArea;


}
