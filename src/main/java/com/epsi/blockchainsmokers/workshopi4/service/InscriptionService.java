package com.epsi.blockchainsmokers.workshopi4.service;

import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;

public interface InscriptionService {
    void inscrireUtilisateur(String email, String motDePasse, String confirmationMotDePasse) throws WorkshopException;
}