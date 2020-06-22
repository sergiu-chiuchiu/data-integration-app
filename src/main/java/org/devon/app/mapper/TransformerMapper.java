package org.devon.app.mapper;

import org.devon.app.dto.RawDataTDto;
import org.devon.app.entities.transformers.AdvertisementPageTTransformer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public abstract class TransformerMapper {

    @BeforeMapping
    protected void extractZone(RawDataTDto rawDataTDto, @MappingTarget AdvertisementPageTTransformer advertisementPageTTransformer){
        advertisementPageTTransformer.convertZone(rawDataTDto.getZone());
        advertisementPageTTransformer.convertToPriceList(rawDataTDto.getPrice());
        advertisementPageTTransformer.convertToUsefulArea(rawDataTDto.getUsefulArea());
        advertisementPageTTransformer.convertToBuiltSurface(rawDataTDto.getBuiltSurface());
        advertisementPageTTransformer.convertFloorItem(rawDataTDto.getFloor());
        advertisementPageTTransformer.convertToLastUpdated(rawDataTDto.getLastUpdate());
        advertisementPageTTransformer.convertToNoOfBathrooms(rawDataTDto.getNoOfBathrooms());
    }

    @Mappings({

            @Mapping(source = "pageTitle", target = "pageTitle"),
            @Mapping(target = "usefulArea", ignore = true),
            @Mapping(target = "builtSurface", ignore = true),
            @Mapping(target = "noOfBathrooms", ignore = true),
            @Mapping(target = "estateState", source = "state"),
            @Mapping(target = "buildingType", source = "propertyType")
    })
    public abstract AdvertisementPageTTransformer rawDataTDtoToAdvertisementPageTTransformer(RawDataTDto rawDataTDto);
}
