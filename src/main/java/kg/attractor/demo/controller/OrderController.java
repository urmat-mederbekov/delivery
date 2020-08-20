package kg.attractor.demo.controller;

import kg.attractor.demo.form.OrderForm;
import kg.attractor.demo.model.Role;
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
@RequestMapping("/orders")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final PropertiesService propertiesService;
    private final UserService userService;

    @GetMapping
    public String getOrders(Model model,Pageable pageable, Principal principal, HttpServletRequest uriBuilder){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(orderService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "orders";
    }

    @GetMapping("/search")
    public String search(Model model,Pageable pageable, Principal principal, HttpServletRequest uriBuilder, @RequestParam String query){

        userService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(orderService.search(query, pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "search";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable String id, Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        model.addAttribute("order", orderService.getOrderById(Long.parseLong(id)));

        if (userService.existsByEmailAndRole(principal.getName(), Role.ROLE_ADMIN)){

            return "order";
        }else if (userService.existsByEmailAndRole(principal.getName(), Role.ROLE_USER)){
            return "curier-order";

        }
        return "order";
    }

    @GetMapping("/add-order")
    public String addOrder(Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        return "add-order";
    }

    @PostMapping
    public String addOrder(@Valid OrderForm orderForm,
                          BindingResult validationResult,
                          RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/orders/add-order";
        }

        orderService.addOrder(orderForm);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/assign-courier")
    public String assignCourier(@PathVariable String id, Model model, Principal principal){
        userService.checkUserPresence(model, principal);
        model.addAttribute("order", orderService.getOrderById(Long.parseLong(id)));
        model.addAttribute("users", userService.getAllCouriers());
        return "assign-curier";
    }

    @PostMapping("/{id}/assign-courier")
    public String assignCourier(@PathVariable String id, @RequestParam Long userId){

        orderService.assignCourier(Long.parseLong(id), userId);
        return "redirect:/orders/{id}";
    }


    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable String id){


        orderService.deleteOrderById(Long.parseLong(id));
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrder(@PathVariable String id, Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        model.addAttribute("order", orderService.getOrderById(Long.parseLong(id)));
        model.addAttribute("users", userService.getAllCouriers());
        return "edit-order";
    }

    @PostMapping("/{id}/edit")
    public String editOrder(@PathVariable String id, @Valid OrderForm orderForm,
                            BindingResult validationResult,
                            RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/orders/{id}/edit";
        }

        orderService.editOrderById(Long.parseLong(id), orderForm);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/state")
    public String editOrder(@PathVariable String id){

        orderService.changeOrderState(Long.parseLong(id));
        return "redirect:/users/{id}/orders";
    }
}
