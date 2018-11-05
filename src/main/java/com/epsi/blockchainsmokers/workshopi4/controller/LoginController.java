package com.epsi.blockchainsmokers.workshopi4.controller;

import com.epsi.blockchainsmokers.workshopi4.config.PageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return PageMapping.INDEX;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return PageMapping.INDEX;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return PageMapping.LOGIN;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.invalidate();
        return PageMapping.INDEX;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost() {
        // connecter l'utilisateur
        return PageMapping.INDEX;
    }

}
