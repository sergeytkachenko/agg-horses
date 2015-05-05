package app.crud;

import app.model.CategoriesSite;
import app.model.Category;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoriesSitesRepositories extends PagingAndSortingRepository<CategoriesSite, Integer> {
    public List<CategoriesSite> findAll();

    public CategoriesSite findById(Integer id);
    public List<CategoriesSite> findBySite(Site site);
    public CategoriesSite findBySiteAndCategory(Site site, Category category);

    @Transactional
    public void deleteAll();

    public CategoriesSite save(CategoriesSite s);

}
