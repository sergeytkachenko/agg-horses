package app.crud;

import app.model.Category;
import app.model.CategoryProperty;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryPropertiesRepositories extends PagingAndSortingRepository<CategoryProperty, Integer> {
    public List<CategoryProperty> findAll();

    public CategoryProperty findById(Integer id);
    public List<CategoryProperty> findBySite(Site site);

    @Transactional
    public void deleteAll();

    public CategoryProperty save(CategoryProperty s);

}
