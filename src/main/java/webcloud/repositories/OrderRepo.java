package webcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webcloud.entity.Order;
import webcloud.entity.User;

import java.util.List;

@Repository
public interface OrderRepo
        extends JpaRepository<Order, Long> {
    List<Order> findByUser(User c);
}
