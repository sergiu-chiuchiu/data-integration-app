package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class AdvertisementPageTransformer {

    private String pageTitle;
    private String region;
    private String neighbourhood;
    private Map<String, Double> priceList;
    private Integer noOfRooms;
    private Double usefulArea;
    private Double builtSurface;
    private String partitioning;
    private Integer floorNo;
    private Integer totalFloors;
    private Integer noOfBathrooms;
    private Integer constructionYear;
    private String pageId;
    private Calendar lastUpdated;
    private String pageSource;
    private String image1;
    private String image2;

}
