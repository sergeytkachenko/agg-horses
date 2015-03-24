package app.crud;

import app.model.Product;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductsRepositories extends PagingAndSortingRepository<Product, Integer> {
    public List<Product> findAll();

    public Product findById(Integer id);
    public Product findByPathHash(String hash);

    @Transactional
    public void deleteAll();

    public Product save(Product s);

}
