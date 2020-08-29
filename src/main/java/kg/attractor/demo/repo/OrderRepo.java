package kg.attractor.demo.repo;

import kg.attractor.demo.model.Order;
import kg.attractor.demo.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Page<Order> getAllByUserId(Pageable pageable, Long id);

    Page<Order> getAllByGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(String good, String destination, String user_name, Pageable pageable);
}
