package com.elplaneador.accounting.repository;

import com.elplaneador.accounting.entity.LibroDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroDiarioRepository extends JpaRepository<LibroDiario, Long> {
    List<LibroDiario> findByFechaBetweenOrderByFechaDesc(LocalDate fechaInicio, LocalDate fechaFin);
    List<LibroDiario> findAllByOrderByFechaDesc();
}
