package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public abstract class AdvertisementPageTransformer {

    private String pageTitle;
    private String city;
    private String neighbourhood;
    private List<String> priceList;
    private Integer noOfRooms;
    private Double usefulArea;
    private Double builtSurface;
    private String partitioning;
    private Integer floorNo;
    private Integer totalFloors;
    private Integer noOfBathrooms;
    private String constructionYear;
    private String pageId;
    private String lastUpdated;
    private String image1;
    private String image2;

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPriceList(List<String> priceList) {
        this.priceList = priceList;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public void setUsefulArea(Double usefulArea) {
        this.usefulArea = usefulArea;
    }

    public void setBuiltSurface(Double builtSurface) {
        this.builtSurface = builtSurface;
    }

    public void setPartitioning(String partitioning) {
        this.partitioning = partitioning;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public void setNoOfBathrooms(Integer noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
