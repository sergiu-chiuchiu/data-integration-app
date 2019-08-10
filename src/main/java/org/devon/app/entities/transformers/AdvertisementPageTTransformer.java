package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class AdvertisementPageTTransformer extends AdvertisementPageTransformer {

    String state;
    String propertyType;

    public void setState(String state) {
        this.state = state;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
