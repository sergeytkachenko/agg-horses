package app.crud;

import app.model.Proxy;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyRepositories extends PagingAndSortingRepository<Proxy, Integer> {
    public List<Proxy> findAll();

    public Proxy findById(Integer id);
    public Proxy findByIp (String ip);

    @Transactional
    public void deleteAll();

    public Proxy save(Proxy s);

}
