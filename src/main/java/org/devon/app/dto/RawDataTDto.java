package org.devon.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.enums.Partitioning;

@Getter
@Setter
@NoArgsConstructor
public class RawDataTDto {
    String pageTitle;
    String zone;
    String price;
    String noOfRooms;
    String usefulArea;
    String builtSurface;
    String partitioning;
    String floor;
    String noOfBathrooms;
    String constructionYear;
    String totalFloors;
    String pageId;
    String lastUpdate;
    String state;
    String propertyType;
    String image1;
    String image2;

    @JsonCreator
    public RawDataTDto(@JsonProperty("PageTitle") String pageTitle,
                       @JsonProperty("Zone") String zone,
                       @JsonProperty("Price") String price,
                       @JsonProperty("NoOfRooms") String noOfRooms,
                       @JsonProperty("UsefulArea") String usefulArea,
                       @JsonProperty("BuiltSurface") String builtSurface,
                       @JsonProperty("Partitioning") String partitioning,
                       @JsonProperty("Floor") String floor,
                       @JsonProperty("NoOfBathrooms") String noOfBathrooms,
                       @JsonProperty("ConstructionYear") String constructionYear,
                       @JsonProperty("TotalFloors") String totalFloors,
                       @JsonProperty("PageId") String pageId,
                       @JsonProperty("LastUpdate") String lastUpdate,
                       @JsonProperty("State") String state,
                       @JsonProperty("PropertyType") String propertyType,
                       @JsonProperty("Image1") String image1,
                       @JsonProperty("Image2") String image2) {
        this.pageTitle = pageTitle;
        this.zone = zone;
        this.price = price;
        this.noOfRooms = noOfRooms;
        this.usefulArea = usefulArea;
        this.builtSurface = builtSurface;
        this.partitioning = partitioning;
        this.floor = floor;
        this.noOfBathrooms = noOfBathrooms;
        this.constructionYear = constructionYear;
        this.totalFloors = totalFloors;
        this.pageId = pageId;
        this.lastUpdate = lastUpdate;
        this.state = state;
        this.propertyType = propertyType;
        this.image1 = image1;
        this.image2 = image2;
    }
}
