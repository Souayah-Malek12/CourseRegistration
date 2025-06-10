package org.codetime.gestioncoursetudiant.Controller;


import org.codetime.gestioncoursetudiant.Entity.User;
import org.codetime.gestioncoursetudiant.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value="signup")
    public String signup(Model model){
        model.addAttribute("signup", new User());
        return "signup";
    }

    @RequestMapping(value = "saveSignup", method = RequestMethod.POST)
    public String saveSignup(@Valid @ModelAttribute("signup") User user,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            if (user.getPassword().equals(user.getPasswordCheck())) {
                if (userService.findByUsername(user.getUsername()) == null) {
                    userService.encryptPassword(user);
                    user.setRole("USER");
                    userService.saveUser(user);
                    redirectAttributes.addFlashAttribute("message", "User registered successfully!");
                    return "redirect:/login";
                } else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords do not match");
            }
        }

        return "signup";
    }

}
