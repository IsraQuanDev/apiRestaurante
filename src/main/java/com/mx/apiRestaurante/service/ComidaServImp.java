package com.mx.apiRestaurante.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.mx.apiRestaurante.entity.Comidas;
import com.mx.apiRestaurante.repository.ComidasRepository;

@Service // Indica que esta clase es un servicio de Spring
public class ComidaServImp {

    // Inyección del repositorio ComidasRepository para interactuar con la base de datos
    @Autowired 
    ComidasRepository repository;

    // Método para listar todas las comidas
    @Transactional(readOnly = true) // Transacción de solo lectura para optimizar
    public List<Comidas> listar() {
        // Recupera todas las comidas de la base de datos
        List<Comidas> lista = repository.findAll();
        return lista;
    }

    // Método para guardar una comida con validación de nombre duplicado
    @Transactional
    public boolean guardar(Comidas comida) {
        boolean bandera = false;
        
        // Itera sobre todas las comidas existentes para verificar si el nombre ya está registrado
        for(Comidas c : repository.findAll()) {
            if(c.getNombre().equals(comida.getNombre())) {
                bandera = true; // Si el nombre ya existe, bandera es true
                break;
            }
        }
        
        // Si no existe un nombre duplicado, guarda la comida
        if(!bandera) {
            repository.save(comida);
        }
        return bandera; // Retorna true si el nombre estaba duplicado, false si se guardó correctamente
    }

    // Método para buscar una comida por su ID
    @Transactional
    public Optional<Comidas> buscarPorId(Long id) {
        return repository.findById(id); // Busca y retorna la comida con el ID especificado
    }

    // Método para editar una comida
    @Transactional
    public Comidas editarComida(Comidas comida) {
        // Busca la comida existente por ID
        Optional<Comidas> comidaExistente = repository.findById(comida.getId());
        
        if (!comidaExistente.isPresent()) {
            // Si no se encuentra la comida, lanza una excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida no encontrada con el id: " + comida.getId());
        }

        // Si existe, guarda la comida editada
        return repository.save(comida);
    }

    // Método para eliminar una comida por ID
    @Transactional
    public void eliminarPorId(Long id) {
        Optional<Comidas> comidaExistente = repository.findById(id);

        if (!comidaExistente.isPresent()) {
            // Si no existe la comida con el ID, lanza una excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comida no encontrada con el id: " + id);
        }

        // Si la comida existe, la elimina
        repository.deleteById(id);
    }

    // Método para buscar comidas por nombre (sin importar mayúsculas o minúsculas)
    @Transactional
    public List<Comidas> buscarPorNombre(String nombre) {
        return repository.findAll().stream()
            .filter(c -> c.getNombre().equalsIgnoreCase(nombre)) // Insensible a mayúsculas/minúsculas
            .collect(Collectors.toList()); // Retorna una lista de comidas que coinciden con el nombre
    }

    // Método para buscar comidas por fecha de venta
    @Transactional
    public List<Comidas> buscarPorFecha(Date fecha) {
        // Busca las comidas por fecha
        List<Comidas> comidas = repository.findByFechaVenta(fecha);

        if (!comidas.isEmpty()) {
            // Si encuentra comidas, las retorna
            return comidas;
        } else {
            // Si no encuentra comidas para la fecha, lanza una excepción
            throw new IllegalArgumentException("No se encontraron comidas para la fecha: " + fecha);
        }
    }

    // Método para eliminar comidas por nombre
    @Transactional
    public String eliminarComidaPorNombre(String nombre) {
        // Busca todas las comidas con el nombre especificado
        List<Comidas> comidas = repository.findByNombre(nombre);

        if (!comidas.isEmpty()) {
            // Si se encuentran comidas con ese nombre, las elimina
            repository.deleteAll(comidas);
            return "Comidas con nombre '" + nombre + "' eliminadas correctamente."; // Mensaje de éxito
        } else {
            // Si no se encuentran comidas, lanza una excepción
            throw new IllegalArgumentException("No se encontraron comidas con el nombre: " + nombre);
        }
    }
}
