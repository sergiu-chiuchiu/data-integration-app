package org.devon.app.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.devon.app.ConsoleInteractions;
import org.devon.app.comparator.AdvertisementPageComparator;
import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.enums.PageSource;
import org.devon.app.entities.transformers.AdvertisementPageTransformer;
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
        header.set(0, header.get(0).substring(1));
        return header;
    }

    @Override
    public Boolean checkForDuplicatesBetween(AdvertisementPage apToCheck) {
        List<AdvertisementPage> apList = advertisementPageRepository.findAll();

        for (AdvertisementPage existingAp : apList) {
            Double duplScore = advertisementPageComparator.comparePages(existingAp, apToCheck);
            Boolean result = duplScore >= Constants.DUPLICATE_TRESHOLD;
            if (result) {
                System.out.println("For new record with pageID " + apToCheck.getPageId()
                        + " there have been found a potential duplicate in record with page ID "
                        + existingAp.getPageId() + " with a duplicate percentage of: " + duplScore * 100 + "%");
                System.out.println("New record: " + apToCheck.toString());
                System.out.println("Existing record: " + existingAp.toString());
                Boolean shouldSaveUserDecision = consoleInteractions.askForDuplicatePersistence();
                return shouldSaveUserDecision;
            }
        }
        return true;
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
        Boolean isDateEqual = sdf.format(ap.getEditDate().getTime()).equals(sdf.format(apTransformer.getLastUpdated().getTime()));
        if (isDateEqual) {
            return false;
        }
        return true;
    }

    <E extends AdvertisementPageTransformer> void validateTransformerAndPersist(E transformer) {
        Boolean isDuplicateWithin = checkForDuplicatesWithin(transformer);
        if (isDuplicateWithin) {
            Boolean isModifiedRecord = checkForRecordUpdate(transformer);
            if (isModifiedRecord) {
                AdvertisementPage updatedAp = transformer.mapTransformerToEntity();
                AdvertisementPage ap = advertisementPageRepository.findByPageId(updatedAp.getPageId());
                modelMapper.map(updatedAp, ap);
                advertisementPageRepository.save(ap);
            }
        } else {
            AdvertisementPage ap = transformer.mapTransformerToEntity();
            Boolean shouldSave = checkForDuplicatesBetween(ap);
            if (shouldSave) {
                advertisementPageRepository.save(ap);
            }
        }
    }

}
