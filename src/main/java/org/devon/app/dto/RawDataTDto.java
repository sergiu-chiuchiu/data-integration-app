package org.devon.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RawDataTDto extends RawDataDto {
    String price;
    String estateState;

    @JsonCreator
    public RawDataTDto(
            @JsonProperty("PageTitle") String pageTitle,
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
            @JsonProperty("State") String estateState,
            @JsonProperty("PropertyType") String buildingType,
            @JsonProperty("Image1") String image1,
            @JsonProperty("Image2") String image2
    ) {
        super(pageTitle, zone, noOfRooms, usefulArea, builtSurface, partitioning, floor, noOfBathrooms, constructionYear,
                totalFloors, buildingType, pageId, lastUpdate, image1, image2);
        this.price = price;
        this.estateState = estateState;
    }
}
