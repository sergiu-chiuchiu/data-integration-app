package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public abstract class AdvertisementPageTransformer {

    String pageTitle;
    String zone;
    String price;
    Integer noOfRooms;
    String usefulArea;
    String builtSurface;
    String partitioning;
    String floor;
    String totalFloors;
    Integer noOfBathrooms;
    String constructionYear;
    String pageId;
    String lastUpdated;
    String image1;
    String image2;

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public void setUsefulArea(String usefulArea) {
        this.usefulArea = usefulArea;
    }

    public void setBuiltSurface(String builtSurface) {
        this.builtSurface = builtSurface;
    }

    public void setPartitioning(String partitioning) {
        this.partitioning = partitioning;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setTotalFloors(String totalFloors) {
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
