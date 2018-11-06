package com.epsi.blockchainsmokers.workshopi4.service;

import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    String findMotDePasseByEmail(String email);

    Utilisateur findOneById(Long id);

    Utilisateur findOneByEmail(String email);

    List<Utilisateur> findAllByIdIn(List<Long> ids);

    void utilisateurConnecte(Long id) throws WorkshopException;

}