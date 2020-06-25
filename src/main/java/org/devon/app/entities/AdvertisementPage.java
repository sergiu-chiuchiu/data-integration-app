package org.devon.app.entities;

import lombok.*;
import org.devon.app.entities.enums.PageSource;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Set<Price> priceList;

    String pageId;
//    String publishDate;
    @Temporal(TemporalType.DATE)
    Calendar editDate;
    @Enumerated(EnumType.STRING)
    PageSource pageSource;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Set<Image> images;

}

