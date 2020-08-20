package kg.attractor.demo.service;

import kg.attractor.demo.dto.OrderDTO;
import kg.attractor.demo.form.OrderForm;
import kg.attractor.demo.model.Order;
import kg.attractor.demo.repo.OrderRepo;
import kg.attractor.demo.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    public void addOrder(OrderForm orderForm){

        orderRepo.save(Order.builder()
                .good(orderForm.getGood())
                .destination(orderForm.getDestination())
                .orderedAt(LocalDateTime.now())
                .orderedTo(orderForm.getOrderedTo())
                .build());
    }

    public void assignCourier(Long orderId, Long userId){
        Order order = orderRepo.findById(orderId).get();
        order.setUser(userRepo.findById(userId).get());

        orderRepo.save(order);
    }

    public Page<OrderDTO> getAllByUser(Pageable pageable, Principal principal){
        return orderRepo.getAllByUserEmail(pageable, principal.getName()).map(OrderDTO::from);
    }

    public Page<OrderDTO> getAll(Pageable pageable){
        return orderRepo.findAll(pageable).map(OrderDTO::from);
    }

    public Page<OrderDTO> search(String query, Pageable pageable){
        return orderRepo.getAllByGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(query, query, query, pageable).map(OrderDTO::from);
    }

    public Page<OrderDTO> searchByUser(Principal principal, String query, Pageable pageable){
        return orderRepo.getAllByUserEmailAndGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(principal.getName(), query, query, query, pageable).map(OrderDTO::from);
    }

    public OrderDTO getOrderById(Long id){
        return OrderDTO.from(orderRepo.findById(id).get());
    }

    public void editOrderById(Long id, OrderForm orderForm){
        Order order = orderRepo.findById(id).get();

        order.setGood(orderForm.getGood());
        order.setDestination(orderForm.getDestination());
        order.setOrderedTo(orderForm.getOrderedTo());
        order.setUser(userRepo.findById(orderForm.getUserId()).get());

        orderRepo.save(order);
    }

    public void changeOrderState(Long id){
        Order order = orderRepo.findById(id).get();
        order.setState(order.getState().changeState());
        orderRepo.save(order);
    }

    public void deleteOrderById(Long id){
        orderRepo.deleteById(id);
    }
}
