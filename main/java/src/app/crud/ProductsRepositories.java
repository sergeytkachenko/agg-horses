package app.crud;

import app.model.Product;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ProductsRepositories extends PagingAndSortingRepository<Product, Integer> {
    public List<Product> findAll();

    public Product findById(Integer id);
    public Product findByPathHash(String hash);
    public Product findByPathHashAndSite(String hash, Site site);
    public List<Product> findByDateUpdate(Date date);
    public List<Product> findByDateUpdateAndHtmlIsNotNull(Date date);

    @Transactional
    public void deleteAll();

    public Product save(Product s);
}
