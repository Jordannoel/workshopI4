package com.epsi.blockchainsmokers.workshopi4.dao;

import com.epsi.blockchainsmokers.workshopi4.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilletDao extends JpaRepository<Billet, Long> {

    int countByNumero(int numero);

    List<Billet> findByNumero(int numero);

    Billet findOneById(Long id);

    @Query("SELECT distinct u FROM billet u WHERE u.id in :ids ")
    List<Billet> findAllByIdIn(@Param("ids") List<Long> ids);
}