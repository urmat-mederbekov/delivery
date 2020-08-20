package kg.attractor.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String good;

    @Column
    private String destination;

    @Column
    private LocalDateTime orderedAt;

    @Column
    private LocalDateTime orderedTo;

    @Column
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private State state = State.NOT_COMPLETED;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
}
