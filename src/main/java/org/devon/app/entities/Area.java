package org.devon.app.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@NoArgsConstructor
@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder
@ToString
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double usefulArea;
    Double builtSurface;
    Double totalUsefulArea;

}
