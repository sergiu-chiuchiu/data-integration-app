package org.devon.app.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.enums.PageSource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AdvertisementPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String pageTitle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Estate estate;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Price> priceList;
    String pageId;
//    String publishDate;
    @Temporal(TemporalType.DATE)
    Calendar editDate;
    @Enumerated(EnumType.STRING)
    PageSource pageSource;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Image> imageList = new ArrayList<>();


}

