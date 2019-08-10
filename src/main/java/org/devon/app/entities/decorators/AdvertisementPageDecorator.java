package org.devon.app.entities.decorators;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devon.app.entities.AdvertisementPage;

@NoArgsConstructor @Getter @Setter
public abstract class AdvertisementPageDecorator extends AdvertisementPage {

    protected AdvertisementPage advertisementPage;

    public AdvertisementPageDecorator(AdvertisementPage advertisementPage) {
        this.advertisementPage = advertisementPage;
    }




}
