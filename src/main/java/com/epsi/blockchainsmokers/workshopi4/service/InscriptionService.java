package com.epsi.blockchainsmokers.workshopi4.service;

import com.epsi.blockchainsmokers.workshopi4.exception.WorkshopException;

public interface InscriptionService {
    void inscrireUtilisateur(String username, String email, String motDePasse, String confirmationMotDePasse, boolean approbation) throws WorkshopException;
}