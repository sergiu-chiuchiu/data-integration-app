package org.devon.app.entities;

import lombok.*;
import org.devon.app.entities.enums.PageSource;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Entity
@Getter
@Builder
@ToString
public class AdvertisementPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String pageTitle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Estate estate;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Price> priceList = new HashSet<>();

    String pageId;
//    String publishDate;
    @Temporal(TemporalType.DATE)
    Calendar editDate;
    @Enumerated(EnumType.STRING)
    PageSource pageSource;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Image> images = new HashSet<>();

}

