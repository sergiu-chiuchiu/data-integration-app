package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AdvertisementPageTTransformer extends AdvertisementPageTransformer {

    private String estateState;
    private String propertyType;

    @Override
    public void setPageTitle(String pageTitle) {
        super.setPageTitle(pageTitle.trim());
    }

    public void convertZone(String zone) {
        try {
            int separatorIdx = zone.indexOf(",");
            String region = zone.substring(0, separatorIdx).trim();
            String neighbourhood = zone.substring(separatorIdx + 1).trim();
            super.setRegion(region);
            super.setNeighbourhood(neighbourhood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToPriceList(String price) {
        try {
            Map<String, Double> priceMap = new HashMap<>();
            price = price
                    .replace(" ", "")
                    .replace("€", "")
                    .trim();
            priceMap.put("EUR", Double.valueOf(price));
            super.setPriceList(priceMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToNoOfRooms(String noOfRoomsStr) {
        try {
            Integer noOfRooms = Integer.valueOf(noOfRoomsStr.trim());
            super.setNoOfRooms(noOfRooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToUsefulArea(String usefulAreaStr) {
        Double usefulArea = Double.valueOf(usefulAreaStr.trim());
        super.setUsefulArea(usefulArea);
    }

    public void convertToBuiltSurface(String builtSurfaceStr) {
        try {
            int idx = builtSurfaceStr.indexOf("m");
            builtSurfaceStr = builtSurfaceStr.substring(0, idx).trim();
            Number bsNumber = NumberFormat.getInstance(Locale.FRANCE).parse(builtSurfaceStr);
            Double builtSurface = bsNumber.doubleValue();
            super.setBuiltSurface(builtSurface);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    @Override
    public void setPartitioning(String partitioning) {
        super.setPartitioning(partitioning.trim());
    }

    public void convertFloorItem(String floorStr) {
        try {
            Integer floor;
            if (floorStr.equalsIgnoreCase("Parter")) {
                floor = 0;
            } else {
                floor = Integer.valueOf(floorStr);
            }
            super.setFloorNo(floor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToNoOfBathrooms(String noOfBathStr) {
        try {
            Integer noOfBahtrooms = Integer.valueOf(noOfBathStr);
            super.setNoOfBathrooms(noOfBahtrooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToConstructionYear(String consYearStr) {
        try {
            Integer constructionYear = Integer.valueOf(consYearStr);
            super.setConstructionYear(constructionYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToBuildingType(String bldType) {
        if (bldType.equalsIgnoreCase("apartament")) {
            bldType = "bloc de apartamente";
        } else {
            bldType = bldType.trim();
        }
        super.setBuildingType(bldType);
    }


    public void convertToTotalFloors(String totalFloorsStr) {
        try {
            Integer totalFloors = Integer.valueOf(totalFloorsStr);
            super.setTotalFloors(totalFloors);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPageId(String pageId) {
        super.setPageId(pageId.trim());
    }

    public void convertToLastUpdated(String lastUpdatedStr) {
        try {
            String updatepoint = "Data modificarii:";
            int length = updatepoint.length();
            int startIdx = lastUpdatedStr.indexOf(updatepoint);
            lastUpdatedStr = lastUpdatedStr
                    .substring(startIdx + length);
            lastUpdatedStr = lastUpdatedStr
                    .substring(0, lastUpdatedStr.indexOf("în urmă"))
                    .trim();

            Calendar lastUpdated = Calendar.getInstance();
            Integer lastUpdatedInt;
            if (lastUpdatedStr.contains("ore")) {
                lastUpdatedInt = Integer.valueOf(lastUpdatedStr
                        .replace("ore", "")
                        .trim()
                );
                lastUpdated.add(Calendar.HOUR, -lastUpdatedInt);
            } else if (lastUpdatedStr.contains("o zi")) {
                lastUpdated.add(Calendar.DATE, -1);
            } else if (lastUpdatedStr.contains("zile")) {
                lastUpdatedInt = Integer.valueOf(lastUpdatedStr
                        .replace("zile", "")
                        .trim()
                );
                lastUpdated.add(Calendar.DATE, -lastUpdatedInt);
            }
            super.setLastUpdated(lastUpdated);
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImage1(String image1) {
        super.setImage1(image1.trim());
    }

    @Override
    public void setImage2(String image2) {
        super.setImage2(image2.trim());
    }

    public void setEstateState(String estateState) {
        this.estateState = estateState.trim();
    }
}
