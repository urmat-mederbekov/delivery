package kg.attractor.demo.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderForm {

    @NotBlank(message = "Обязательное поле")
    private String good;

    @NotBlank(message = "Обязательное поле")
    private String destination;

    @Future(message = "Невозможно создать событие с прошедшой датой")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Обязательное поле")
    private LocalDateTime orderedTo;

//    @NotNull(message = "Обязательное поле")
    private Long userId;
}
