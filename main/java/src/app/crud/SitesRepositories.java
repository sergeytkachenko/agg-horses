package app.crud;

import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SitesRepositories extends PagingAndSortingRepository<Site, Integer> {
    public List<Site> findAll();

    public Site findById(Integer id);

    @Transactional
    public void deleteAll();

    public Site save(Site s);

}
