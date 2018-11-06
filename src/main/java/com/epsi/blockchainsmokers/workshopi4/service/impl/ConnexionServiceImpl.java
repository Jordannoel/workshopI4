package com.epsi.blockchainsmokers.workshopi4.service.impl;

import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;
import com.epsi.blockchainsmokers.workshopi4.service.ConnexionService;
import com.epsi.blockchainsmokers.workshopi4.service.UtilisateurService;
import com.epsi.blockchainsmokers.workshopi4.utils.WorkshopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionServiceImpl implements ConnexionService {

    private UtilisateurService utilisateurService;

    @Autowired
    public ConnexionServiceImpl(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Utilisateur connecterUtilisateur(String email, String motDePasse) throws WorkshopException {

        WorkshopException ex = new WorkshopException();

        if (email != null && !email.equals("")) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                ex.addMessage("email", "Merci de saisir une adresse e-mail valide.");
            }
        } else {
            ex.addMessage("email", "Merci de saisir votre adresse e-mail.");
        }
        if (motDePasse != null && !motDePasse.equals("")) {
            if (!motDePasseCorrect(email, motDePasse)) {
                ex.addMessage("motDePasse", "Le mot de passe n'est pas correct.");
            }
        } else {
            ex.addMessage("motDePasse", "Merci de saisir votre mot de passe.");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }

        return utilisateurService.findOneByEmail(email);
    }

    private boolean motDePasseCorrect(String email, String motDePasse) {
        return WorkshopUtils.sha256(motDePasse).equals(utilisateurService.findMotDePasseByEmail(email));
    }
}