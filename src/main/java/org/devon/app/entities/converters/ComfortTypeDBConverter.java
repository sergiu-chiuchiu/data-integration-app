package org.devon.app.entities.converters;

import org.devon.app.entities.enums.ComfortType;
import org.devon.app.entities.enums.Partitioning;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ComfortTypeDBConverter implements AttributeConverter<ComfortType, String> {


    @Override
    public String convertToDatabaseColumn(ComfortType attribute) {
        if (attribute == null) return null;

        return attribute.getComfortType();
    }

    @Override
    public ComfortType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        return Stream.of(ComfortType.values())
                .filter(comfortType -> comfortType.getComfortType().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
