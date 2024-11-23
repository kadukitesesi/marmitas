package com.kadukitesesi.marmitas.repository;

import com.kadukitesesi.marmitas.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel,Long> {

}
