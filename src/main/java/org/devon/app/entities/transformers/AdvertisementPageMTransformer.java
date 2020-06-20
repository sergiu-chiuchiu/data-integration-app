package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.Setter;
import org.devon.app.entities.*;
import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.CurrencyType;
import org.devon.app.entities.enums.PageSource;
import org.devon.app.entities.enums.Partitioning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@Component
public class AdvertisementPageMTransformer extends AdvertisementPageTransformer {
    private static Logger LOG = LoggerFactory
            .getLogger(AdvertisementPageMTransformer.class);

    private Double totalUsefulArea;
    private String comfort;
    private Integer noOfKitchens;
    private String resistanceStructure;
    private String floorsBreakdown;
    private Integer noOfBalconies;

    public AdvertisementPageMTransformer() {
        setPageSource("M");
    }

    @Override
    public AdvertisementPage mapTransformerToEntity() {
        AdvertisementPageMTransformer apTr = this;

        Area area = Area.builder()
                .usefulArea(apTr.getUsefulArea())
                .builtSurface(apTr.getBuiltSurface())
                .totalUsefulArea(apTr.getTotalUsefulArea())
                .build();

        Construction construction = Construction.builder()
                .constructionYear(apTr.getConstructionYear())
                .resistanceStructure(apTr.getResistanceStructure())
                .buildingType(apTr.getBuildingType())
                .floor(apTr.getFloorNo())
                .totalNoOfFloors(apTr.getTotalFloors())
                .build();

        Rooms rooms = Rooms.builder()
                .noOfRooms(apTr.getNoOfRooms())
                .noOfKitchens(apTr.getNoOfKitchens())
                .noOfBalconies(apTr.getNoOfBalconies())
                .noOfBathrooms(apTr.getNoOfBathrooms())
                .build();

        Estate estate = Estate.builder()
                .partitioning(Partitioning.fromString(apTr.getPartitioning()))
                .comfortType(ComfortType.fromString(apTr.getComfort()))
                .region(apTr.getRegion())
                .neighbourhood(apTr.getNeighbourhood())
                .area(area)
                .construction(construction)
                .rooms(rooms)
                .build();

        AdvertisementPage ap = AdvertisementPage.builder()
                .pageTitle(apTr.getPageTitle())
                .estate(estate)
                .pageId(apTr.getPageId())
                .editDate(apTr.getLastUpdated())
                .pageSource(PageSource.fromString(apTr.getPageSource()))
                .build();


        Set<Price> priceList = new HashSet<>();
        for (Map.Entry<String, Double> priceEntry : apTr.getPriceList().entrySet()) {
            Price p = Price.builder()
                    .currencyType(CurrencyType.fromString(priceEntry.getKey()))
                    .price(priceEntry.getValue())
                    .advertisementPage(ap)
                    .build();
            priceList.add(p);
        }

        Set<Image> imageList = new HashSet<>();
        Image image = Image.builder()
                .imageName(apTr.getImage1())
                .advertisementPage(ap)
                .build();
        imageList.add(image);
        image = Image.builder()
                .imageName(apTr.getImage2())
                .advertisementPage(ap)
                .build();
        imageList.add(image);

        ap.setImages(imageList);
        ap.setPriceList(priceList);

        return ap;
    }

    public void convertZone(String zone) {
        try {
            int idx = zone.lastIndexOf("-");
            zone = zone.substring(0, idx);

            idx = zone.indexOf(", zona");
            String region = zone.substring(0, idx).trim();
            String neighbourhood = zone.substring(idx + 6).trim();

            super.setRegion(region);
            super.setNeighbourhood(neighbourhood);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void convertToPriceList(String prices) {
        try {
            Map<String, Double> priceList = new HashMap<>();
            prices = prices.replace(".", "");

            String price;
            price = prices.substring(0, prices.indexOf("RON") + 3);
            prices = prices.replace(price, "");
            price = price.replace(" RON", "").trim();
            priceList.put("RON", Double.valueOf(price));

            price = prices.substring(0, prices.indexOf("USD") + 3);
            prices = prices.replace(price, "");
            price = price.replace(" USD", "").trim();
            priceList.put("USD", Double.valueOf(price));

            price = prices.substring(0, prices.indexOf("EUR") + 3);
            price = price.replace(" EUR", "").trim();
            priceList.put("EUR", Double.valueOf(price));

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
            } else if (lastUpdatedStr.toLowerCase().contains("ieri")) {
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
