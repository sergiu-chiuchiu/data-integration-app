package org.devon.app.entities.converters;

import org.devon.app.entities.enums.Partitioning;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PartitioningConverter implements AttributeConverter<Partitioning, String> {

    @Override
    public String convertToDatabaseColumn(Partitioning attribute) {
        if (attribute == null) return null;
        return attribute.name().toLowerCase();
    }

    @Override
    public Partitioning convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Stream.of(Partitioning.values())
                .filter(c -> c.name().equals(dbData.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
