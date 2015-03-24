package app.crud;

import app.model.CategoriesSite;
import app.model.PropertiesSite;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PropertiesSiteRepositories extends PagingAndSortingRepository<PropertiesSite, Integer> {
    public List<PropertiesSite> findAll();

    public PropertiesSite findById(Integer id);
    public List<PropertiesSite> findByCategorySites(CategoriesSite cs);

    @Transactional
    public void deleteAll();

    public PropertiesSite save(PropertiesSite s);

}
