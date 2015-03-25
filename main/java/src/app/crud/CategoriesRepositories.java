package app.crud;

import app.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoriesRepositories extends PagingAndSortingRepository<Category, Integer> {
    public List<Category> findAll();

    public Category findById(Integer id);

    @Transactional
    public void deleteAll();

    public Category save(Category s);

}
