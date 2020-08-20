package kg.attractor.demo.dto;

import kg.attractor.demo.model.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private Long id;
    private String email;
    private String name;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
