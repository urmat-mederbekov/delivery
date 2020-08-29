package kg.attractor.demo.service;

import kg.attractor.demo.dto.UserDTO;
import kg.attractor.demo.exception.ResourceNotFoundException;
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

    public UserDTO getUserById(Long id){
        return UserDTO.from(userRepo.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public UserDTO getUserByEmail(String email){
        return UserDTO.from(userRepo.findByEmail(email).orElseThrow(ResourceNotFoundException::new));
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
                model.addAttribute("admin", UserDTO.from(userRepo.findByEmail(principal.getName()).orElseThrow(ResourceNotFoundException::new)));
            }else if (userRepo.existsByEmailAndRole(principal.getName(), Role.ROLE_USER)){
                model.addAttribute("courier", UserDTO.from(userRepo.findByEmail(principal.getName()).orElseThrow(ResourceNotFoundException::new)));

            }
        }
    }

    public void checkUserDifference(Principal principal, Long id){

        User user = userRepo.findByEmail(principal.getName()).orElseThrow(ResourceNotFoundException::new);

            if(!user.getId().equals(id) && !user.getRole().equals(Role.ROLE_ADMIN)){
                throw new NullPointerException();
            }
    }

    public boolean existsByEmailAndRole(String name, Role role) {
        return userRepo.existsByEmailAndRole(name, role);
    }

    public void editUserById(Long id, UserForm userForm) {

        User user = userRepo.findById(id).orElseThrow(ResourceNotFoundException::new);

        user.setName(userForm.getName());
        userRepo.save(user);
    }
}
