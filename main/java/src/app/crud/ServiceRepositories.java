package app.crud;

import app.model.Service;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceRepositories extends PagingAndSortingRepository<Service, Integer> {
    public List<Service> findAll();

    public Service findById(Integer id);
    public Service findByTitleAndSite(String title, Site site);

    @Transactional
    public void deleteAll();

    public Service save(Service s);

}
