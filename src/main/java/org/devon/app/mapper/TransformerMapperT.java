package org.devon.app.mapper;

import org.devon.app.dto.RawDataTDto;
import org.devon.app.entities.transformers.AdvertisementPageTTransformer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public abstract class TransformerMapperT {

    @BeforeMapping
    protected void convertFieldsBefore(RawDataTDto rawDataTDto, @MappingTarget AdvertisementPageTTransformer advertisementPageTTransformer) {
        advertisementPageTTransformer.convertZone(rawDataTDto.getZone());
        advertisementPageTTransformer.convertToPriceList(rawDataTDto.getPrice());
        advertisementPageTTransformer.convertToUsefulArea(rawDataTDto.getUsefulArea());
        advertisementPageTTransformer.convertToBuiltSurface(rawDataTDto.getBuiltSurface());
        advertisementPageTTransformer.convertToLastUpdated(rawDataTDto.getLastUpdate());
        advertisementPageTTransformer.convertToNoOfBathrooms(rawDataTDto.getNoOfBathrooms());
    }

    @Mappings({
            @Mapping(target = "usefulArea", ignore = true),
            @Mapping(target = "builtSurface", ignore = true),
            @Mapping(target = "noOfBathrooms", ignore = true),
    })
    public abstract AdvertisementPageTTransformer rawDataTDtoToAdvertisementPageTTransformer(RawDataTDto rawDataTDto);

    @AfterMapping
    protected void convertFieldsAfter(RawDataTDto rawDataTDto, @MappingTarget AdvertisementPageTTransformer advertisementPageTTransformer) {
        advertisementPageTTransformer.convertFloorItem(rawDataTDto.getFloor());
    }

}
