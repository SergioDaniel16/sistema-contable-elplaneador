package com.elplaneador.accounting.repository;

import com.elplaneador.accounting.entity.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    List<Bitacora> findTop100ByOrderByFechaHoraDesc();
    List<Bitacora> findByServerIp(String serverIp);
    List<Bitacora> findByTablaAfectada(String tablaAfectada);
    List<Bitacora> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
