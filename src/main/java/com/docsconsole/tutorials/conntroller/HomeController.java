package com.docsconsole.tutorials.conntroller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = {"/"})
    public String welcome(ModelMap model) {
        return "welcome";
    }

    @GetMapping(value = {"/home"})
    public String home(ModelMap model) {
        return "home";
    }

    @GetMapping(value = {"/admin"})
    public String admin(ModelMap model) {
        return "admin";
    }

    @GetMapping(value = {"/login"})
    public String login(ModelMap model, @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }
    @GetMapping(value = "/accessDenied")
    public String accessDenied(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addAttribute("username", userDetail.getUsername());

        }
        return "accessDenied";

    }
}