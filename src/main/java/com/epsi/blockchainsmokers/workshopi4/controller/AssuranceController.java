package com.epsi.blockchainsmokers.workshopi4.controller;

import com.epsi.blockchainsmokers.workshopi4.config.ApplicationUrl;
import com.epsi.blockchainsmokers.workshopi4.config.PageMapping;
import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;
import com.epsi.blockchainsmokers.workshopi4.service.ConnexionService;
import com.epsi.blockchainsmokers.workshopi4.service.InscriptionService;
import com.epsi.blockchainsmokers.workshopi4.service.ReclamationService;
import com.epsi.blockchainsmokers.workshopi4.service.SouscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AssuranceController {

    private SouscriptionService souscriptionService;
    private ReclamationService reclamationService;

    private static final String REDIRECT = "redirect:";

    @Autowired
    public AssuranceController(SouscriptionService souscriptionService, ReclamationService reclamationService) {
        this.souscriptionService = souscriptionService;
        this.reclamationService = reclamationService;
    }


    /***********************************************INSCRIPTION***************************************************/

    @RequestMapping(value = ApplicationUrl.RECLAMATION, method = RequestMethod.GET)
    public String reclamation() {
        return PageMapping.RECLAMATION;
    }

    /*@RequestMapping(value = ApplicationUrl.SIGNIN, method = RequestMethod.POST)
    public String inscriptionPost(HttpServletRequest req, RedirectAttributes red) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        String confirmationMotDePasse = req.getParameter("confirmationMotDePasse");
        boolean approbation = Boolean.valueOf(req.getParameter("approbation"));
        try {
            inscriptionService.inscrireUtilisateur(email, motDePasse, confirmationMotDePasse, approbation);
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("id", utilisateur.getId());
            red.addFlashAttribute("email", utilisateur.getEmail());
            return REDIRECT + ApplicationUrl.SIGNIN_OK;
        } catch (WorkshopException e) {
            red.addFlashAttribute("errors", e.getMessages());
            return REDIRECT + ApplicationUrl.SIGNIN;
        }
    }

    @RequestMapping(value = ApplicationUrl.SIGNIN_OK, method = RequestMethod.GET)
    public String inscriptionValidee(HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        modelMap.put("email", session.getAttribute("email"));
        return PageMapping.SIGNIN_OK;
    }*/

    /***********************************************CONNEXION***************************************************/

    @RequestMapping(value = ApplicationUrl.SOUSCRIPTION, method = RequestMethod.GET)
    public String souscription() {
        return PageMapping.SOUSCRIPTION;
    }

    /*@RequestMapping(value = ApplicationUrl.LOGIN, method = RequestMethod.POST)
    public String connexionPost(HttpServletRequest req, ModelMap modelMap) {
        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");
        try {
            Utilisateur utilisateur = connexionService.connecterUtilisateur(email, motDePasse);
            HttpSession session = req.getSession();
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("id", utilisateur.getId());
            return REDIRECT + ApplicationUrl.INDEX;
        } catch (WorkshopException e) {
            modelMap.put("errors", e.getMessages());
            return PageMapping.LOGIN;
        }
    }*/

    /***********************************************DECONNEXION***************************************************/

    /*@RequestMapping(value = ApplicationUrl.LOGOUT, method = RequestMethod.GET)
    public String deconnexionPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return REDIRECT + ApplicationUrl.INDEX;
    }*/
}
