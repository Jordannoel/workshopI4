package com.epsi.blockchainsmokers.workshopi4.service.impl;

import com.epsi.blockchainsmokers.workshopi4.dao.UtilisateurDao;
import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;
import com.epsi.blockchainsmokers.workshopi4.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public String findMotDePasseByEmail(String email) {
        List<Utilisateur> utilisateurs = utilisateurDao.findByMail(email);
        if (utilisateurs.size() == 0) {
            return null;
        } else {
            return utilisateurs.get(0).getMdp();
        }
    }

    @Override
    public Utilisateur findOneById(Long id) {
        return utilisateurDao.findOneById(id);
    }

    @Override
    public Utilisateur findOneByEmail(String email) {
        List<Utilisateur> utilisateurs = utilisateurDao.findByMail(email);
        if (utilisateurs.size() == 0) {
            return null;
        } else {
            return utilisateurs.get(0);
        }
    }

    @Override
    public List<Utilisateur> findAllByIdIn(List<Long> ids) {
        return utilisateurDao.findAllByIdIn(ids);
    }

    @Override
    public void utilisateurConnecte(Long id) throws WorkshopException {
        WorkshopException ex = new WorkshopException();
        if (id == null) {
            ex.addMessage("connexion", "Vous n'êtes pas connecté");
        }
        if (ex.mustBeThrown()) {
            throw ex;
        }
    }
}
