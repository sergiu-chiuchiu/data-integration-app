package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.ConsoleInteractions;
import org.devon.app.comparator.AdvertisementPageComparator;
import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.Estate;
import org.devon.app.entities.Image;
import org.devon.app.entities.Price;
import org.devon.app.entities.enums.PageSource;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
import org.devon.app.mapper.TransformerMapperT;
import org.devon.app.repositories.AdvertisementPageRepository;
import org.devon.app.services.IintegrationService;
import org.devon.app.utils.Constants;
import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public abstract class AIntegrationService implements IintegrationService {

    public static final boolean IS_NOT_FROM_INTEGRATION_ENDPOINT = false;
    public static final boolean IS_FROM_INTEGRATION_ENDPOINT = true;

    AdvertisementPageRepository advertisementPageRepository;
    ModelMapper modelMapper;
    AdvertisementPageComparator advertisementPageComparator;
    ConsoleInteractions consoleInteractions;

    CSVReader csvReaderInit(BufferedReader br) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        return new CSVReaderBuilder(br).withCSVParser(parser).build();
    }

    List<String> getDataHeader(CSVReader csvReader) {
        List<String> header = null;
        try {
            header = Arrays.asList(csvReader.readNext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert header != null;
        header.set(0, header.get(0).substring(1));
        return header;
    }

    @Override
    public Boolean checkForDuplicatesBetween(AdvertisementPage apToCheck, boolean isFromIntegrationEndpoint) {
        List<AdvertisementPage> apList = advertisementPageRepository.findAll();

        for (AdvertisementPage existingAp : apList) {
            Double duplScore = advertisementPageComparator.comparePages(existingAp, apToCheck);
            boolean result = duplScore >= Constants.DUPLICATE_TRESHOLD;
            if (result) {
                System.out.println("For new record with pageID " + apToCheck.getPageId()
                        + " there have been found a potential duplicate in record with page ID "
                        + existingAp.getPageId() + " with a duplicate percentage of: " + duplScore * 100 + "%");
                System.out.println("New record: " + apToCheck.toString());
                System.out.println("Existing record: " + existingAp.toString());

                boolean shouldSave = false;
                if (!isFromIntegrationEndpoint) {
                    shouldSave = consoleInteractions.askForDuplicatePersistence();
                }
                logShouldSaveConclusion(shouldSave);
                return shouldSave;
            }
        }
        return true;
    }

    private void logShouldSaveConclusion(boolean shouldSave) {
        if (shouldSave) {
            System.out.println("Near duplicate found but saved at user request");
        } else {
            System.out.println("Near duplicate found aborted saving");
        }
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForDuplicatesWithin(E apTransformer) {
        List<AdvertisementPage> advertisementPageList = advertisementPageRepository.findByPageSource(PageSource.fromString(apTransformer.getPageSource()));

        for (AdvertisementPage ap : advertisementPageList) {
            if (apTransformer.getPageId().equals(ap.getPageId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <E extends AdvertisementPageTransformer> Boolean checkForRecordUpdate(E apTransformer) {
        AdvertisementPage ap = advertisementPageRepository.findByPageId(apTransformer.getPageId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        boolean isDateEqual = sdf.format(ap.getEditDate().getTime()).equals(sdf.format(apTransformer.getLastUpdated().getTime()));
        return !isDateEqual;
    }

    <E extends AdvertisementPageTransformer> void validateTransformerAndPersist(E transformer) {
        validateTransformerAndPersist(transformer, IS_NOT_FROM_INTEGRATION_ENDPOINT);
    }

    <E extends AdvertisementPageTransformer> void validateTransformerAndPersist(E transformer, boolean isFromIntegrationEndpoint) {
        boolean isDuplicateWithin = checkForDuplicatesWithin(transformer);
        if (isDuplicateWithin) {
            boolean isModifiedRecord = checkForRecordUpdate(transformer);
            if (isModifiedRecord) {
                AdvertisementPage updatedAp = transformer.mapTransformerToEntity();
                AdvertisementPage ap = advertisementPageRepository.findByPageId(updatedAp.getPageId());
                setIds(ap, updatedAp);
                modelMapper.map(updatedAp, ap);
                advertisementPageRepository.save(ap);
            }
        } else {
            AdvertisementPage ap = transformer.mapTransformerToEntity();
            boolean shouldSave = checkForDuplicatesBetween(ap, isFromIntegrationEndpoint);
            if (shouldSave) {
                advertisementPageRepository.save(ap);
            }
        }
    }

    private void setIds(AdvertisementPage ap, AdvertisementPage updatedAp) {
        Estate e = ap.getEstate();
        Estate updatedE = updatedAp.getEstate();

        updatedE.setId(e.getId());
        updatedE.getConstruction().setId(e.getConstruction().getId());
        updatedE.getArea().setId(e.getArea().getId());
        updatedE.getRooms().setId(e.getRooms().getId());

        for (Image i : updatedAp.getImages()) {
            for(Image iAp : ap.getImages()) {
                if (iAp.getImageName().equalsIgnoreCase(i.getImageName())) {
                    i.setId(iAp.getId());
                }
            }
        }

        for (Price p : updatedAp.getPriceList()) {
            for(Price pAp : ap.getPriceList()) {
                if (pAp.getCurrencyType().equals(p.getCurrencyType())) {
                    p.setId(pAp.getId());
                }
            }
        }
    }

}
