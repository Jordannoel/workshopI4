package com.epsi.blockchainsmokers.workshopi4.controller;

import com.epsi.blockchainsmokers.workshopi4.config.ApplicationUrl;
import com.epsi.blockchainsmokers.workshopi4.config.PageMapping;
import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;
import com.epsi.blockchainsmokers.workshopi4.service.ConnexionService;
import com.epsi.blockchainsmokers.workshopi4.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private ConnexionService connexionService;
    private InscriptionService inscriptionService;

    @Autowired
    public LoginController(ConnexionService connexionService, InscriptionService inscriptionService) {
        this.connexionService = connexionService;
        this.inscriptionService = inscriptionService;
    }

    private static final String REDIRECT = "redirect:";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return PageMapping.INDEX;
    }

    @RequestMapping(value = ApplicationUrl.INDEX, method = RequestMethod.GET)
    public String index() {
        return PageMapping.INDEX;
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN, method = RequestMethod.GET)
    public String inscription() {
        return PageMapping.SIGNIN;
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN, method = RequestMethod.POST)
    public String inscriptionPost(HttpServletRequest req, ModelMap modelMap) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("password");
        String confirmationMotDePasse = req.getParameter("repassword");
        try {
            inscriptionService.inscrireUtilisateur(email, motDePasse, confirmationMotDePasse);
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getMail());
            session.setAttribute("id", utilisateur.getId());
            return REDIRECT + ApplicationUrl.LOGIN;
        } catch (WorkshopException e) {
            modelMap.put("errors", e.getMessages());
            return PageMapping.SIGNIN;
        }
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN_OK, method = RequestMethod.GET)
    public String inscriptionValidee(HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        modelMap.put("username", session.getAttribute("username"));
        modelMap.put("email", session.getAttribute("email"));
        return PageMapping.INDEX_CLIENT;
    }

    @RequestMapping(value = ApplicationUrl.LOGIN, method = RequestMethod.GET)
    public String connexion() {
        return PageMapping.LOGIN;
    }

    @RequestMapping(value = ApplicationUrl.LOGIN, method = RequestMethod.POST)
    public String connexionPost(HttpServletRequest req, ModelMap modelMap) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("password");
        try {
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getMail());
            session.setAttribute("id", utilisateur.getId());
            return REDIRECT + ApplicationUrl.INDEX_CLIENT;
        } catch (WorkshopException e) {
            modelMap.put("errors", e.getMessages());
            return PageMapping.LOGIN;
        }
    }

    @RequestMapping(value = ApplicationUrl.LOGOUT, method = RequestMethod.POST)
    public String deconnexionPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return REDIRECT + ApplicationUrl.INDEX;
    }
}
