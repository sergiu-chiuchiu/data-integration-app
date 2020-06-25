package org.devon.app.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public abstract class RawDataDto {
    String pageTitle;
    String zone;
    String noOfRooms;
    String usefulArea;
    String builtSurface;
    String partitioning;
    String floor;
    String noOfBathrooms;
    String constructionYear;
    String totalFloors;
    String buildingType;
    String pageId;
    String lastUpdate;
    String image1;
    String image2;
}
