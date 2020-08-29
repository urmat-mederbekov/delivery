package kg.attractor.demo.service;

import kg.attractor.demo.dto.OrderDTO;
import kg.attractor.demo.exception.ResourceNotFoundException;
import kg.attractor.demo.form.OrderForm;
import kg.attractor.demo.model.Order;
import kg.attractor.demo.model.User;
import kg.attractor.demo.repo.OrderRepo;
import kg.attractor.demo.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        Order order = orderRepo.findById(orderId).orElseThrow(ResourceNotFoundException::new);
        order.setUser(userRepo.findById(userId).orElseThrow(ResourceNotFoundException::new));

        orderRepo.save(order);
    }

    public Page<OrderDTO> getAllByUserId(Pageable pageable,Long id){
        User user = userRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return orderRepo.getAllByUserId(pageable, user.getId()).map(OrderDTO::from);
    }

    public Page<OrderDTO> getAll(Pageable pageable){
        return orderRepo.findAll(pageable).map(OrderDTO::from);
    }

    public Page<OrderDTO> search(String query, Pageable pageable){
        return orderRepo.getAllByGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(query, query, query, pageable).map(OrderDTO::from);
    }

    public Page<OrderDTO> searchByUser(Principal principal, String query, Pageable pageable){
        Page<Order> orders = orderRepo.getAllByGoodContainsIgnoreCaseOrDestinationContainsIgnoreCaseOrUserNameContainsIgnoreCase(query, query,query, pageable);
        orders = new PageImpl<>(orders.filter(order -> order.getUser().getEmail().equals(principal.getName())).toList());
        return orders.map(OrderDTO::from);
    }

    public OrderDTO getOrderById(Long id){
        return OrderDTO.from(orderRepo.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public void editOrderById(Long id, OrderForm orderForm){
        Order order = orderRepo.findById(id).orElseThrow(ResourceNotFoundException::new);

        order.setGood(orderForm.getGood());
        order.setDestination(orderForm.getDestination());
        order.setOrderedTo(orderForm.getOrderedTo());
        order.setUser(userRepo.findById(orderForm.getUserId()).orElseThrow(ResourceNotFoundException::new));

        orderRepo.save(order);
    }

    public void changeOrderState(Long id){
        Order order = orderRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        order.setState(order.getState().changeState());
        orderRepo.save(order);
    }

    public void deleteOrderById(Long id){
        orderRepo.deleteById(id);
    }
}
