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

    private String EstateState;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
