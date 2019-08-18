package org.devon.app.repositories;

import org.devon.app.entities.AdvertisementPage;
import org.devon.app.entities.enums.PageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementPageRepository extends JpaRepository<AdvertisementPage, Long> {
    public List<AdvertisementPage> findByPageSource(PageSource pageSource);

    public AdvertisementPage findByPageId(String pageId);


}
