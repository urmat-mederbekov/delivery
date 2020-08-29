package kg.attractor.demo.controller;

import kg.attractor.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping
    public String indexPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "login";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/accessdenied")
    public String accessdenied(Model model, Principal principal){

        userService.checkUserPresence(model, principal);
        return "resource-not-found";
    }
}
