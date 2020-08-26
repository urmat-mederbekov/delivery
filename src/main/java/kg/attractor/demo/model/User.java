package kg.attractor.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    @Builder.Default
    private Boolean enabled = true;

    @Column
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
}
