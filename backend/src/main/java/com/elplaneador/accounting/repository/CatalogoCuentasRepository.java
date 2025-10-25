package com.elplaneador.accounting.repository;

import com.elplaneador.accounting.entity.CatalogoCuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoCuentasRepository extends JpaRepository<CatalogoCuentas, Long> {
    Optional<CatalogoCuentas> findByCodigo(String codigo);
    List<CatalogoCuentas> findByActivaTrueOrderByCodigo();
    List<CatalogoCuentas> findByTipo(String tipo);
    boolean existsByCodigo(String codigo);
}