package org.devon.app.entities;

import lombok.*;
import org.devon.app.entities.enums.CurrencyType;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder
@ToString
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;
    @Enumerated(EnumType.STRING)
    CurrencyType currencyType;

    @ManyToOne
    @ToString.Exclude
    AdvertisementPage advertisementPage;


}
