package ru.dorogov.spring.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dorogov.spring.models.User;
import ru.dorogov.spring.DAO.UserRepository;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid User user,
                               BindingResult bindingResult) {
        if (userRepository.existsByLogin(user.getLogin())) {
            bindingResult.rejectValue("login", "error.login", "Пользователь с таким логином уже зарегистрирован");
            return "register";
        }
        if(bindingResult.hasErrors())
            return "register";

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
}