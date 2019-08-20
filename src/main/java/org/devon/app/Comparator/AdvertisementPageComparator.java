package org.devon.app.Comparator;

import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.Price;
import org.devon.app.entities.enums.Partitioning;
import org.devon.app.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdvertisementPageComparator {

    private String[] highScore = {"isPageTitleDuplicate"};
    private String[] mediumScore = {"isPriceDuplicate","isUsefulAreaDuplicate", "isBuiltSurfaceDuplicate", "isNeighbourhoodDuplicate", "isConstructionYearDuplicate"};
    private String[] lowScore = {"isRegionDuplicate", "isPartitioningDuplicate", "isNoOfRoomsDuplicate", "isNoOfBathroomsDuplicate", "isFloorDuplicate", "isTotalFloorsDuplicate"};


    public Boolean comparePages(AdvertisementPage existingPage, AdvertisementPage newPage) {

        Map<String, Boolean> duplicateFields = new HashMap<>();

        duplicateFields.put("isPageTitleDuplicate", checkPageTitleDuplicate(existingPage.getPageTitle(), newPage.getPageTitle()));
        duplicateFields.put("isUsefulAreaDuplicate", checkDoubleDuplicate(existingPage.getEstate().getArea().getUsefulArea(), newPage.getEstate().getArea().getUsefulArea()));
        duplicateFields.put("isBuiltSurfaceDuplicate", checkDoubleDuplicate(existingPage.getEstate().getArea().getBuiltSurface(), newPage.getEstate().getArea().getBuiltSurface()));
        duplicateFields.put("isRegionDuplicate", isRegionDuplicate(existingPage.getEstate().getRegion(), newPage.getEstate().getRegion()));
        duplicateFields.put("isNeighbourhoodDuplicate", isNeighbourhoodDuplicate(existingPage.getEstate().getNeighbourhood(), newPage.getEstate().getNeighbourhood()));
        duplicateFields.put("isPriceDuplicate", isPriceDuplicate(existingPage.getPriceList(), newPage.getPriceList()));
        duplicateFields.put("isPartitioningDuplicate", isPartitioningDuplicate(existingPage.getEstate().getPartitioning(), newPage.getEstate().getPartitioning()));
        duplicateFields.put("isNoOfRoomsDuplicate", checkIntegerDuplicate(existingPage.getEstate().getRooms().getNoOfRooms(), newPage.getEstate().getRooms().getNoOfRooms()));
        duplicateFields.put("isNoOfBathroomsDuplicate", checkIntegerDuplicate(existingPage.getEstate().getRooms().getNoOfBathrooms(), newPage.getEstate().getRooms().getNoOfBathrooms()));
        duplicateFields.put("isFloorDuplicate", checkIntegerDuplicate(existingPage.getEstate().getConstruction().getFloor(), newPage.getEstate().getConstruction().getFloor()));
        duplicateFields.put("isTotalFloorsDuplicate", checkIntegerDuplicate(existingPage.getEstate().getConstruction().getTotalNoOfFloors(), newPage.getEstate().getConstruction().getTotalNoOfFloors()));
        duplicateFields.put("isConstructionYearDuplicate", checkIntegerDuplicate(existingPage.getEstate().getConstruction().getConstructionYear(), newPage.getEstate().getConstruction().getConstructionYear()));

        Double duplicateScore = calculateDuplicateScore(duplicateFields);

        Boolean result = duplicateScore >= Constants.DUPLICATE_TRESHOLD;

        return result;
    }

    private Double calculateDuplicateScore(Map<String, Boolean> duplicateFields) {
        Double score = 0.0;

        Map<String[], Double> categoryScore = new HashMap<>();
        categoryScore.put(highScore, 15.0);
        categoryScore.put(mediumScore, 10.0);
        categoryScore.put(lowScore, 5.5);

        Double maxScore = 0.0;
        for (String[] category : categoryScore.keySet()) {
            for (String field : category) {
                if (duplicateFields.get(field)) {
                    score += categoryScore.get(category);
                }
            }
            maxScore += categoryScore.get(category) * category.length;
        }

        return score / maxScore;
    }

    private Boolean checkPageTitleDuplicate(String existingVal, String newVal) {
        if (existingVal.equalsIgnoreCase(newVal)) {
            return true;
        }
        return false;
    }

    private Boolean checkDoubleDuplicate(Double existingVal, Double newVal) {
        if (existingVal.equals(newVal)) {
            return true;
        }
        return false;
    }

    private Boolean isRegionDuplicate(String existingVal, String newVal) {
        if (existingVal.equalsIgnoreCase(newVal)) {
            return true;
        }
        return false;
    }

    private Boolean isNeighbourhoodDuplicate(String existingVal, String newVal) {
        if (existingVal.equalsIgnoreCase(newVal)) {
            return true;
        }
        return false;
    }

    private Boolean isPriceDuplicate(List<Price> existingVal, List<Price> newVal) {
        Boolean isPriceDupl = false;
        outerLoop:
        for (Price existingPrice : existingVal) {
            for (Price newPrice : newVal) {
                if (existingPrice.getCurrencyType().equals(newPrice.getCurrencyType())) {
                    if (existingPrice.getPrice().equals(newPrice.getPrice())) {
                        isPriceDupl = true;
                        break outerLoop;
                    }
                }
            }
        }
        return isPriceDupl;
    }

    private Boolean isPartitioningDuplicate(Partitioning existingVal, Partitioning newVal) {
        if (existingVal == newVal) {
            return true;
        }
        return false;
    }


    private Boolean checkIntegerDuplicate(Integer existingVal, Integer newVal) {
        if (existingVal.equals(newVal)) {
            return true;
        }
        return false;
    }


}
