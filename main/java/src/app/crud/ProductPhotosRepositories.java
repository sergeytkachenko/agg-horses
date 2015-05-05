package app.crud;

import app.model.Product;
import app.model.ProductPhoto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductPhotosRepositories extends PagingAndSortingRepository<ProductPhoto, Integer> {
    public List<ProductPhoto> findAll();

    public ProductPhoto findById(Integer id);
    public ProductPhoto findByOutHash(String hash);
    public List<ProductPhoto> findByIsDownloaded(boolean downloaded);
    public List<ProductPhoto> findByPhashIsNull();

    @Transactional
    public void deleteAll();

    public ProductPhoto save(ProductPhoto s);

}
