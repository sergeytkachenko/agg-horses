package app.crud;

import app.model.Comment;
import app.model.Product;
import app.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CommentsRepositories extends PagingAndSortingRepository<Comment, Integer> {
    public List<Comment> findAll();

    public Comment findById(Integer id);

    @Transactional
    public void deleteAll();

    public Comment save(Comment s);
}
