package org.devon.app.entities;

import lombok.*;
import org.devon.app.entities.enums.CurrencyType;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Builder
public class Price {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;
    @Enumerated(EnumType.STRING)
    CurrencyType currencyType;

    @ManyToOne
    AdvertisementPage advertisementPage;


}
