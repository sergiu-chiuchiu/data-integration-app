package org.devon.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

@NoArgsConstructor
public class AdvertisementPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String pageTitle;
    Estate estate;

    List<Price> priceList;
    String pageId;
    String publishDate;
    String editDate;
    List<Image> imageList = new ArrayList<>();

    public String test() {
        return "test";
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
