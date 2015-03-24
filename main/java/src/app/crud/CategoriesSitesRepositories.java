package app.crud;

import app.model.CategoriesSite;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoriesSitesRepositories extends PagingAndSortingRepository<CategoriesSite, Integer> {
    public List<CategoriesSite> findAll();

    public CategoriesSite findById(Integer id);

    @Transactional
    public void deleteAll();

    public CategoriesSite save(CategoriesSite s);

}
