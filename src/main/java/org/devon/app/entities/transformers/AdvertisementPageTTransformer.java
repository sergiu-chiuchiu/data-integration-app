package org.devon.app.entities.transformers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AdvertisementPageTTransformer extends AdvertisementPageTransformer {

    private String state;
    private String propertyType;

}
