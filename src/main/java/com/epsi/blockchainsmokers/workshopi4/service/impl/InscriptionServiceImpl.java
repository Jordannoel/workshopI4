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

    public void inscrireUtilisateur(String email, String motDePasse, String confirmationMotDePasse) throws WorkshopException {

        WorkshopException ex = new WorkshopException();
        if (email == null || email.equals("")) {
            ex.addMessage("login-error", "Merci de saisir votre adresse e-mail.");
        } else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            ex.addMessage("login-error", "Merci de saisir une adresse mail valide.");
        }
        if (emailDejaExistant(email)) {
            ex.addMessage("login-error", "Un compte est déjà associé à cette adresse e-mail");
        }

        if (motDePasse == null || motDePasse.length() < 8) {
            ex.addMessage("login-error", "Le mot de passe doit contenir au moins 8 caractères.");
        }
        if (motDePasse != null && !motDePasse.equals(confirmationMotDePasse)) {
            ex.addMessage("login-error", "Les deux mots de passe ne sont pas identiques.");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }

        utilisateurDao.save(new Utilisateur(email, WorkshopUtils.sha256(motDePasse)));
    }

    private boolean emailDejaExistant(String email) {
        return utilisateurDao.countByMail(email) > 0;
    }
}