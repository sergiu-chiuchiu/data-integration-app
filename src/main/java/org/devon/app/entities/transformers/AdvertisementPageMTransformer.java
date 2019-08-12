package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@NoArgsConstructor
@Component
public class AdvertisementPageMTransformer extends AdvertisementPageTransformer {
    private static Logger LOG = LoggerFactory
            .getLogger(AdvertisementPageMTransformer.class);

    private Double totalUsefulArea;
    private String comfort;
    private Integer noOfKitchens;
    private String resistanceStructure;
    private String buildingType;
    private String floorsBreakdown;
    private String noOfBalconies;

    public void convertZone(String zone) {
        try {
            int idx = zone.lastIndexOf("-");
            zone = zone.substring(0, idx);

            idx = zone.indexOf(", zona");
            String city = zone.substring(0, idx).trim();
            String neighbourhood = zone.substring(idx + 6).trim();

            super.setCity(city);
            super.setNeighbourhood(neighbourhood);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void convertToPriceList(String prices) {
        try {
            List<String> priceList = new ArrayList<>();
            String price;
            prices = prices.replace((price = prices.substring(0, prices.indexOf("RON") + 3)), "");
            priceList.add(price);
            prices = prices.replace((price = prices.substring(0, prices.indexOf("USD") + 3)), "");
            priceList.add(price);
            prices.replace((price = prices.substring(0, prices.indexOf("EUR") + 3)), "");
            priceList.add(price);
            super.setPriceList(priceList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void convertToUsefulArea(String usefulArea) {
        try {
            usefulArea = usefulArea.substring(0, usefulArea.indexOf(" mp"));
            Number usefulAreaNumber = null;
            usefulAreaNumber =  NumberFormat.getInstance(Locale.FRANCE).parse(usefulArea);
            super.setUsefulArea(usefulAreaNumber.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToTotalUsefulArea(String totalUsefulArea) {
        try {
            totalUsefulArea = totalUsefulArea.substring(0, totalUsefulArea.indexOf(" mp"));
            this.setTotalUsefulArea(Double.valueOf(totalUsefulArea));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToBuiltSurface(String builtSurface) {
        try {
            builtSurface = builtSurface.substring(0, builtSurface.indexOf(" mp"));
            Number builtSurfaceNumber = null;
            builtSurfaceNumber =  NumberFormat.getInstance(Locale.FRANCE).parse(builtSurface);
            super.setBuiltSurface(builtSurfaceNumber.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void convertFloorItem(String floorItem) {
        try {
            Integer floor = null;
            Integer separatorIdx = floorItem.indexOf("/");
            super.setTotalFloors(Integer.valueOf(floorItem.substring(separatorIdx + 1).trim()));
            if (floorItem.contains("Etaj")) {
                floor = Integer.valueOf(floorItem.substring(4, separatorIdx).trim());
            } else if (floorItem.contains("Parter")) {
                floor = 0;
            } else if (floorItem.contains("Demisol")) {
                floor = -1;
            }
            super.setFloorNo(floor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPageTitle(String pageTitle) {
        String pt = pageTitle.replace("\"", "").trim();
        super.setPageTitle(pt == "" ? null : pt);
    }

    public void setTotalUsefulArea(Double totalUsefulArea) {
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
