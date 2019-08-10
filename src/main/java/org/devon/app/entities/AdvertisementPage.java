package org.devon.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    String publishDate;
    String editDate;

    @OneToMany(mappedBy = "advertisementPage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Image> imageList = new ArrayList<>();

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
