package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
                           @RequestParam("minAge") Optional<Integer> minAge,
                           @RequestParam("maxAge") Optional<Integer> maxAge) {
        logger.info("User list page requested");
        List<User> users = userRepository.filterUsers(
                usernameFilter.orElse(null),
                minAge.orElse(null),
                minAge.orElse(null));

        model.addAttribute("users", users); // Либо userRepository.findAll()
        return "users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        logger.info("New user page requested");

        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        logger.info("Edit user page requested");

        model.addAttribute("user", userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Идиот, разуй глаза! Ты что за хрень вводишь в окно браузера?!!")));
        return "user_form";
    }

    @PostMapping
    public String update(@Valid User user, BindingResult result) {
        logger.info("Saving user");

        if (result.hasErrors()) {
            return "user_form";
        }

        if (user.getAge() > 100) {
            result.rejectValue("age", "", "Error message");
            return "user_form";
        }

        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        logger.info("Deleting user with id{}", id);
        userRepository.deleteById(id);
        return "redirect:/user";
    }


    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
