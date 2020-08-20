package kg.attractor.demo.dto;

import kg.attractor.demo.model.State;
import kg.attractor.demo.model.Order;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDTO {

    private Long id;
    private String good;
    private String destination;
    private LocalDateTime orderedAt;
    private LocalDateTime orderedTo;
    private State state;
    private UserDTO user;

    public static OrderDTO from(Order order) {
        if(order.getUser() != null) {
            return builder()
                    .id(order.getId())
                    .good(order.getGood())
                    .destination(order.getDestination())
                    .orderedAt(order.getOrderedAt())
                    .orderedTo(order.getOrderedTo())
                    .state(order.getState())
                    .user(UserDTO.from(order.getUser()))
                    .build();
        }else {
            return builder()
                    .id(order.getId())
                    .good(order.getGood())
                    .destination(order.getDestination())
                    .orderedAt(order.getOrderedAt())
                    .orderedTo(order.getOrderedTo())
                    .state(order.getState())
                    .build();
        }
    }
}
