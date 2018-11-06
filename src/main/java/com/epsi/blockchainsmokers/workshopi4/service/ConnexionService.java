package com.epsi.blockchainsmokers.workshopi4.service;

import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;
import com.epsi.blockchainsmokers.workshopi4.model.Utilisateur;

public interface ConnexionService {
    Utilisateur connecterUtilisateur(String email, String motDePasse) throws WorkshopException;
}