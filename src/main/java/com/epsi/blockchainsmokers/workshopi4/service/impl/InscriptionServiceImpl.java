package com.epsi.blockchainsmokers.workshopi4.service.impl;

import com.epsi.blockchainsmokers.workshopi4.dao.UtilisateurDao;
import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;
import com.epsi.blockchainsmokers.workshopi4.service.InscriptionService;
import com.epsi.blockchainsmokers.workshopi4.utils.WorkshopUtils;
import org.springframework.stereotype.Service;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    private UtilisateurDao utilisateurDao;

    public InscriptionServiceImpl(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public void inscrireUtilisateur(String username, String email, String motDePasse, String confirmationMotDePasse, boolean approbation) throws WorkshopException {

        WorkshopException ex = new WorkshopException();
        if (email == null || email.equals("")) {
            ex.addMessage("email", "Merci de saisir votre adresse e-mail.");
        } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            ex.addMessage("email", "Merci de saisir une adresse mail valide.");
        }
        if (emailDejaExistant(email)) {
            ex.addMessage("email", "Un compte est déjà associé à cette adresse e-mail");
        }

        if (username == null || username.equals("")) {
            ex.addMessage("username", "Merci de saisir votre nom d'utilisateur.");
        } else if (usernameDejaExistant(username)) {
            ex.addMessage("username", "Ce nom d'utilisateur existe déjà.");
        }

        if (motDePasse == null || motDePasse.length() < 8) {
            ex.addMessage("motDePasse", "Le mot de passe doit contenir au moins 8 caractères.");
        }
        if (motDePasse != null && !motDePasse.equals(confirmationMotDePasse)) {
            ex.addMessage("confirmationMotDePasse", "Les deux mots de passe ne sont pas identiques.");
        }
        if (!approbation) {
            ex.addMessage("approbation", "Vous devez accepter les conditions.");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }

        utilisateurDao.save(new Utilisateur(username, email, WorkshopUtils.sha256(motDePasse)));
    }

    private boolean usernameDejaExistant(String username) {
        return utilisateurDao.countByUsername(username) > 0;
    }

    private boolean emailDejaExistant(String email) {
        return utilisateurDao.countByEmail(email) > 0;
    }
}