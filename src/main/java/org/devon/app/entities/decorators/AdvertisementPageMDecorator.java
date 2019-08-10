package org.devon.app.entities.decorators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.AdvertisementPage;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementPageMDecorator extends AdvertisementPageDecorator {

    public AdvertisementPageMDecorator(AdvertisementPage advertisementPage) {
        super(advertisementPage);
    }

    @Override
    public void setPageTitle(String page) {
        advertisementPage.setPageTitle("dsfsd");
    }

}
