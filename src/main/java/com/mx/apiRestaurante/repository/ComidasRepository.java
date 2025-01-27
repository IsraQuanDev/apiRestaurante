package com.mx.apiRestaurante.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiRestaurante.entity.Comidas;

public interface ComidasRepository extends JpaRepository<Comidas, Long> {

    // Método personalizado para buscar comidas por fecha de venta
    // JPA proporciona implementación automática basándose en el nombre del método
    List<Comidas> findByFechaVenta(Date fechaVenta); // Busca todas las comidas que coinciden con la fecha de venta proporcionada

    // Método personalizado para buscar comidas por nombre
    // JPA proporciona implementación automática basándose en el nombre del método
    List<Comidas> findByNombre(String nombre); // Busca todas las comidas que coinciden con el nombre proporcionado
    
}
