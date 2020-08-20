package kg.attractor.demo.form;

import kg.attractor.demo.annotation.UniqueEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    @NotBlank(message = "Обязательное поле")
    @Size(min = 8, max = 24, message = "Пароль должен содержать минимум 8 и максимум 16 символов")
    private String password = "";

    @Email(message = "Неправильный email")
    @NotBlank(message = "Обязательное поле")
    @UniqueEmail
    private String email = "";

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Имя должно содержать только буквы")
    private String name = "";
}
