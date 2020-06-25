package org.devon.app.mapper;

import org.devon.app.dto.RawDataMDto;
import org.devon.app.entities.transformers.AdvertisementPageMTransformer;
import org.mapstruct.*;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public abstract class TransformerMapperM {


    @BeforeMapping
    protected void convertFieldsBefore(RawDataMDto rawDataMDto, @MappingTarget AdvertisementPageMTransformer advertisementPageMTransformer) {
        advertisementPageMTransformer.convertZone(rawDataMDto.getZone());
        advertisementPageMTransformer.convertToPriceList(rawDataMDto.getPrice());
        advertisementPageMTransformer.convertToUsefulArea(rawDataMDto.getUsefulArea());
        advertisementPageMTransformer.convertToBuiltSurface(rawDataMDto.getBuiltSurface());
        advertisementPageMTransformer.convertToLastUpdated(rawDataMDto.getLastUpdate());
        advertisementPageMTransformer.convertFloorItem(rawDataMDto.getFloor());
        advertisementPageMTransformer.convertToConstructionYear(rawDataMDto.getConstructionYear());
        advertisementPageMTransformer.convertToTotalUsefulArea(rawDataMDto.getTotalUsefulArea());
//        advertisementPageMTransformer.convertToNoOfBathrooms(rawDataMDto.getNoOfBathrooms());
    }

    @Mappings({
            @Mapping(target = "usefulArea", ignore = true),
            @Mapping(target = "builtSurface", ignore = true),
            @Mapping(target = "noOfBathrooms", ignore = true),
            @Mapping(target = "totalFloors", ignore = true),
            @Mapping(target = "floorNo", ignore = true),
            @Mapping(target = "constructionYear", ignore = true),
            @Mapping(target = "totalUsefulArea", ignore = true),
    })
    public abstract AdvertisementPageMTransformer rawDataMDtoToAdvertisementPageMTransformer(RawDataMDto rawDataMDto);
}
