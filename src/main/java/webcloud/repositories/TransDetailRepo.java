package webcloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webcloud.entity.TransactionalDetails;

import java.util.Optional;

@Repository
public interface TransDetailRepo
        extends JpaRepository<TransactionalDetails, Long> {
    Optional<TransactionalDetails> findByOrderId(Long orderId);
}
