package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
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
    private Integer noOfBalconies;

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
            Map<String, Double> priceList = new HashMap<>();
            String price;

            price = prices.substring(0, prices.indexOf("RON") + 3);
            prices = prices.replace(price, "");
            priceList.put("RON", Double.parseDouble(price));

            price = prices.substring(0, prices.indexOf("USD") + 3);
            prices = prices.replace(price, "");
            priceList.put("USD", Double.parseDouble(price));

            price = prices.substring(0, prices.indexOf("EUR") + 3);
            prices = prices.replace(price, "");
            priceList.put("EUR", Double.parseDouble(price));

            super.setPriceList(priceList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void convertToUsefulArea(String usefulArea) {
        try {
            usefulArea = usefulArea.substring(0, usefulArea.indexOf(" mp"));
            Number usefulAreaNumber = null;
            usefulAreaNumber = NumberFormat.getInstance(Locale.FRANCE).parse(usefulArea);
            super.setUsefulArea(usefulAreaNumber.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToTotalUsefulArea(String totalUsefulArea) {
        try {
            totalUsefulArea = totalUsefulArea.substring(0, totalUsefulArea.indexOf(" mp"));
            this.setTotalUsefulArea(Double.valueOf(totalUsefulArea));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToBuiltSurface(String builtSurface) {
        try {
            builtSurface = builtSurface.substring(0, builtSurface.indexOf(" mp"));
            Number builtSurfaceNumber = null;
            builtSurfaceNumber = NumberFormat.getInstance(Locale.FRANCE).parse(builtSurface);
            super.setBuiltSurface(builtSurfaceNumber.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertFloorItem(String floorItem) {
        try {
            Integer floor = null;
            Integer separatorIdx = floorItem.indexOf("/");

            if (floorItem.contains("Etaj")) {
                floor = Integer.valueOf(floorItem
                        .substring(4, separatorIdx)
                        .trim());
            } else if (floorItem.contains("Parter")) {
                floor = 0;
            } else if (floorItem.contains("Demisol")) {
                floor = -1;
            }
            super.setFloorNo(floor);
            super.setTotalFloors(Integer.valueOf(floorItem
                    .substring(separatorIdx + 1)
                    .trim()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToConstructionYear(String conYearStr) {
        try {
            conYearStr = removeSusbstrFromParenthesis(conYearStr);
            Integer conYear = Integer.valueOf(conYearStr.trim());
            super.setConstructionYear(conYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertToNoOfBalconies(String nob) {
        try {
            nob = removeSusbstrFromParenthesis(nob);
            Integer noOfBalconies = Integer.valueOf(nob.trim());
            this.setNoOfBalconies(noOfBalconies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String removeSusbstrFromParenthesis(String item) {
        Integer beginReplIdx;
        if (!(beginReplIdx = item.indexOf("(")).equals(-1)) {
            Integer endReplIdx = item.indexOf(")") + 1;
            item = item
                    .replace(item.substring(beginReplIdx, endReplIdx), "");
        }
        return item;
    }


    public void convertToLastUpdated(String lastUpdatedStr) {
        Calendar lastUpdated = Calendar.getInstance();
        try {
            if (lastUpdatedStr.contains("azi")) {
                // just set the field at the end of method
            } else if (lastUpdatedStr.contains("Ieri")) {
                lastUpdated.add(Calendar.DATE, -1);
            } else if (lastUpdatedStr.contains("Actualizat")) {
                lastUpdatedStr = lastUpdatedStr.replace("Actualizat in ", "").trim();

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date date = sdf.parse(lastUpdatedStr);
                lastUpdated.setTime(date);
            } else {
                lastUpdated = null;
            }
            super.setLastUpdated(lastUpdated);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPageTitle(String pageTitle) {
        String pt = pageTitle.replace("\"", "").trim();
        super.setPageTitle(pt == "" ? null : pt);
    }

}
