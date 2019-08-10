package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class AdvertisementPageMTransformer extends AdvertisementPageTransformer {

    String totalUsefulArea;
    String comfort;
    Integer noOfKitchens;
    String resistanceStructure;
    String buildingType;
    String floorsBreakdown;
    String noOfBalconies;

    @Override
    public void setPageTitle(String pageTitle) {
        String pt = pageTitle.replace("\"", "").trim();
        this.pageTitle = (pt == "" ? null : pt);
    }



    public void setTotalUsefulArea(String totalUsefulArea) {
        this.totalUsefulArea = totalUsefulArea;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public void setNoOfKitchens(Integer noOfKitchens) {
        this.noOfKitchens = noOfKitchens;
    }

    public void setResistanceStructure(String resistanceStructure) {
        this.resistanceStructure = resistanceStructure;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public void setFloorsBreakdown(String floorsBreakdown) {
        this.floorsBreakdown = floorsBreakdown;
    }

    public void setNoOfBalconies(String noOfBalconies) {
        this.noOfBalconies = noOfBalconies;
    }
}
