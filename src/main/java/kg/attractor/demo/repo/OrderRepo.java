package kg.attractor.demo.repo;

import kg.attractor.demo.model.Order;
import kg.attractor.demo.model.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Page<Order> getAllByUserEmail(Pageable pageable, String email);

    Page<Order> getAllByGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(String good, String destination, String user_name, Pageable pageable);

    Page<Order> getAllByUserEmailAndGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(String user_email, String good, String destination, String user_name, Pageable pageable);
}
