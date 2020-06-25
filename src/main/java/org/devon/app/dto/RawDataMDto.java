package org.devon.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RawDataMDto extends RawDataDto {
    String price;
    String totalUsefulArea;
    String comfort;
    String noOfKitchens;
    String resistanceStructure;
    String noOfBalconies;


    @JsonCreator
    public RawDataMDto(
            @JsonProperty("TotalUsefulArea") String  totalUsefulArea,
            @JsonProperty("Comfort") String  comfort,
            @JsonProperty("NoOfKitchens") String  noOfKitchens,
            @JsonProperty("ResistanceStructure") String  resistanceStructure,
            @JsonProperty("NoOfBalconies") String  noOfBalconies,
            @JsonProperty("PageTitle") String pageTitle,
            @JsonProperty("Zone") String zone,
            @JsonProperty("PriceEuro") String price,
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
            @JsonProperty("BuildingType") String buildingType,
            @JsonProperty("Image1") String image1,
            @JsonProperty("Image2") String image2
    ) {
        super(pageTitle, zone, noOfRooms, usefulArea, builtSurface, partitioning, floor, noOfBathrooms, constructionYear,
                totalFloors, buildingType, pageId, lastUpdate, image1, image2);
        this.price = price;
        this.totalUsefulArea = totalUsefulArea;
        this.comfort = comfort;
        this.noOfKitchens = noOfKitchens;
        this.resistanceStructure = resistanceStructure;
        this.noOfBalconies = noOfBalconies;
    }

}
