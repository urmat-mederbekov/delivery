package kg.attractor.demo.service;

import kg.attractor.demo.dto.UserDTO;
import kg.attractor.demo.form.UserForm;
import kg.attractor.demo.model.Role;
import kg.attractor.demo.model.User;
import kg.attractor.demo.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;


    public Page<UserDTO> getAllCouriers(Pageable pageable){
        return userRepo.findAllByRole(Role.ROLE_USER, pageable).map(UserDTO::from);
    }

    public List<UserDTO> getAllCouriers(){

        return userRepo.findAllByRole(Role.ROLE_USER).stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public void addUser(UserForm userForm){

        userRepo.save(User.builder()
        .password(encoder.encode(userForm.getPassword()))
        .email(userForm.getEmail())
        .name(userForm.getName())
        .build());
    }

    public void checkUserPresence(Model model, Principal principal){
        if(principal != null){
            if (userRepo.existsByEmailAndRole(principal.getName(), Role.ROLE_ADMIN)){
                model.addAttribute("admin", UserDTO.from(userRepo.findByEmail(principal.getName()).get()));
            }else if (userRepo.existsByEmailAndRole(principal.getName(), Role.ROLE_USER)){
                model.addAttribute("user", UserDTO.from(userRepo.findByEmail(principal.getName()).get()));

            }
        }
    }

    public boolean existsByEmailAndRole(String name, Role role) {
        return userRepo.existsByEmailAndRole(name, role);
    }

}
