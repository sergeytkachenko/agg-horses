package app.crud;

import app.model.Product;
import app.model.ProductProperty;
import app.model.Property;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductPropertiesRepositories extends PagingAndSortingRepository<ProductProperty, Integer> {
    public List<ProductProperty> findAll();

    public ProductProperty findById(Integer id);
    public ProductProperty findByProductAndProperty(Product p, Property property);

    @Transactional
    public void deleteAll();

    public ProductProperty save(ProductProperty s);

}
