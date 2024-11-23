package com.kadukitesesi.marmitas.repository;

import com.kadukitesesi.marmitas.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel,Long> {

}
