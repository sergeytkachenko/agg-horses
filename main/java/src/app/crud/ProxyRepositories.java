package app.crud;

import app.model.Proxy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyRepositories extends PagingAndSortingRepository<Proxy, Integer> {
    public List<Proxy> findAll();

    public Proxy findById(Integer id);
    public Proxy findByIp (String ip);

    @Query(value = "select * from proxies  where pinged_count > 0 ORDER BY timeout ASC LIMIT 1", nativeQuery = true)
    public Proxy findByBest();

    @Transactional
    public void deleteAll();

    public Proxy save(Proxy s);

}
