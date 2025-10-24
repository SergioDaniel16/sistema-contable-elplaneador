package com.elplaneador.accounting.repository;

import com.elplaneador.accounting.entity.AsientoContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsientoContableRepository extends JpaRepository<AsientoContable, Long> {
}
