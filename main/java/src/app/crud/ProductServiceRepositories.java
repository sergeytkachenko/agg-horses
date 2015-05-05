package app.crud;

import app.model.Product;
import app.model.ProductService;
import app.model.Service;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductServiceRepositories extends PagingAndSortingRepository<ProductService, Integer> {
    public List<ProductService> findAll();

    public ProductService findById(Integer id);
    public ProductService findByProductAndService(Product p, Service s);

    @Transactional
    public void deleteAll();

    public ProductService save(ProductService s);

}
