package com.kadukitesesi.marmitas.repository;

import com.kadukitesesi.marmitas.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    public ClienteModel findByNome(String nome);

    void deleteByNome(String nome);
}
