package com.elplaneador.accounting.service;

import com.elplaneador.accounting.entity.LibroMayor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstadosFinancierosService {
    
    @Autowired
    private LibroMayorService libroMayorService;
    
    public Map<String, Object> generarBalanceGeneral() {
        List<LibroMayor> mayor = libroMayorService.generarLibroMayor();
        
        Map<String, Object> balance = new HashMap<>();
        BigDecimal totalActivos = BigDecimal.ZERO;
        BigDecimal totalPasivos = BigDecimal.ZERO;
        BigDecimal totalCapital = BigDecimal.ZERO;
        
        for (LibroMayor item : mayor) {
            if (item.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
                String tipo = item.getCuenta().getTipo();
                
                if ("ACTIVO".equals(tipo)) {
                    totalActivos = totalActivos.add(item.getSaldo());
                } else if ("PASIVO".equals(tipo)) {
                    totalPasivos = totalPasivos.add(item.getSaldo());
                } else if ("CAPITAL".equals(tipo)) {
                    totalCapital = totalCapital.add(item.getSaldo());
                }
            }
        }
        
        BigDecimal totalPasivoCapital = totalPasivos.add(totalCapital);
        
        balance.put("totalActivos", totalActivos);
        balance.put("totalPasivos", totalPasivos);
        balance.put("totalCapital", totalCapital);
        balance.put("totalPasivoCapital", totalPasivoCapital);
        balance.put("balanceado", totalActivos.compareTo(totalPasivoCapital) == 0);
        
        return balance;
    }
    
    public Map<String, Object> generarEstadoResultados() {
        List<LibroMayor> mayor = libroMayorService.generarLibroMayor();
        
        Map<String, Object> estado = new HashMap<>();
        BigDecimal totalIngresos = BigDecimal.ZERO;
        BigDecimal totalGastos = BigDecimal.ZERO;
        
        for (LibroMayor item : mayor) {
            String tipo = item.getCuenta().getTipo();
            
            if ("INGRESO".equals(tipo)) {
                totalIngresos = totalIngresos.add(item.getSaldo().abs());
            } else if ("GASTO".equals(tipo)) {
                totalGastos = totalGastos.add(item.getSaldo().abs());
            }
        }
        
        BigDecimal utilidad = totalIngresos.subtract(totalGastos);
        
        estado.put("totalIngresos", totalIngresos);
        estado.put("totalGastos", totalGastos);
        estado.put("utilidad", utilidad);
        estado.put("tieneUtilidad", utilidad.compareTo(BigDecimal.ZERO) >= 0);
        
        return estado;
    }
}
