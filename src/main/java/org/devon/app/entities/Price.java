package org.devon.app.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.enums.CurrencyType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Price {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double price;
    @Enumerated(EnumType.STRING)
    CurrencyType currencyType;

    @ManyToOne()
    AdvertisementPage advertisementPage;


}
