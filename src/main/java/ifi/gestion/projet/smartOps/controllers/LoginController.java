package ifi.gestion.projet.smartOps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) boolean error,
                        @RequestParam(name = "logout", required = false) boolean logout,
                        Model model) {
        if (error) {
            model.addAttribute("error", true);
            model.addAttribute("logout", false);
        }

        if (logout) {
            model.addAttribute("logout", true);
            model.addAttribute("error", false);
        }
        return "login";
    }
}
