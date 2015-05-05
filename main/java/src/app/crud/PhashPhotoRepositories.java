package app.crud;

import app.model.PhashPhoto;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhashPhotoRepositories extends PagingAndSortingRepository<PhashPhoto, Integer> {
    public List<PhashPhoto> findAll();

    public PhashPhoto findById(Integer id);

    @Transactional
    public void deleteAll();

    public PhashPhoto save(PhashPhoto s);

}
