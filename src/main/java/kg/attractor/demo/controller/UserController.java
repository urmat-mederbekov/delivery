package kg.attractor.demo.controller;

import kg.attractor.demo.form.UserForm;
import kg.attractor.demo.service.OrderService;
import kg.attractor.demo.service.PropertiesService;
import kg.attractor.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;
    private final PropertiesService propertiesService;
    private final OrderService orderService;

    @GetMapping
    public String getAll(Model model, Pageable pageable, Principal principal, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(userService.getAllCouriers(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "users";
    }

    @GetMapping("/add-user")
    public String addUser(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "user";
    }

    @GetMapping("/{id}/orders")
    public String getOrdersByUser(@PathVariable String id, Model model, Principal principal, Pageable pageable, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(orderService.getAllByUser(pageable, principal), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());

        return "curiers-orders";
    }


    @GetMapping("/{id}/orders/search")
    public String search(@PathVariable String id, Model model,Pageable pageable, Principal principal, HttpServletRequest uriBuilder, @RequestParam String query){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(orderService.searchByUser(principal, query, pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "curiers-search";
    }
    @PostMapping
    public String addUser(@Valid UserForm userForm,
                           BindingResult validationResult,
                           RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/users/add-user";
        }

        userService.addUser(userForm);
        return "redirect:/users";
    }
}