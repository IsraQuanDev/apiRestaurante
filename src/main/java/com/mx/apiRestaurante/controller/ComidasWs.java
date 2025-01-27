package com.mx.apiRestaurante.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mx.apiRestaurante.entity.Comidas;
import com.mx.apiRestaurante.service.ComidaServImp;

@RestController
@RequestMapping(path = "api/ComidasWs")
@CrossOrigin
public class ComidasWs {

    @Autowired
    private ComidaServImp comidaImp;
    
    // Petición GET para listar todas las comidas
    // URL: http://localhost:9000/api/ComidasWs/listar
    @GetMapping("listar")
    public List<Comidas> listar() {
        return comidaImp.listar();
    }

    // Petición POST para guardar una comida
    // URL: http://localhost:9000/api/ComidasWs/guardar
    @PostMapping(path = "guardar")
    public ResponseEntity<?> guardar(@RequestBody Comidas comida) {
        boolean response = comidaImp.guardar(comida);
        if (response) {
            return new ResponseEntity<>("Ese nombre ya existe, no se puede guardar", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(comida, HttpStatus.CREATED);
        }
    }

    // Petición POST para buscar comida por ID
    // URL: http://localhost:9000/api/ComidasWs/buscarPorId
    @PostMapping(path = "buscarPorId")
    public ResponseEntity<?> buscarPorId(@RequestBody Long id) {
        Optional<Comidas> comida = comidaImp.buscarPorId(id);
        if (comida.isPresent()) {
            return new ResponseEntity<>(comida.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el registro con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // Petición POST para editar una comida
    // URL: http://localhost:9000/api/ComidasWs/editar
    @PostMapping(path = "editar")
    public ResponseEntity<?> editarComida(@RequestBody Comidas comida) {
        try {
            Comidas comidaEditada = comidaImp.editarComida(comida);
            return new ResponseEntity<>(comidaEditada, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    // Petición POST para eliminar comida por ID
    // URL: http://localhost:9000/api/ComidasWs/eliminarPorId
    @PostMapping(path = "eliminarPorId")
    public ResponseEntity<?> eliminarPorId(@RequestBody Long id) {
        try {
            comidaImp.eliminarPorId(id);
            return new ResponseEntity<>("Comida eliminada con éxito", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), HttpStatus.NOT_FOUND);
        }
    }

    // Petición POST para buscar comida por nombre
    // URL: http://localhost:9000/api/ComidasWs/buscarPorNombre
    @PostMapping(path = "buscarPorNombre")
    public ResponseEntity<?> buscarPorNombre(@RequestBody Comidas comida) {
        List<Comidas> comidas = comidaImp.buscarPorNombre(comida.getNombre());
        if (comidas.isEmpty()) {
            return new ResponseEntity<>("No se encontraron comidas con el nombre: " + comida.getNombre(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(comidas, HttpStatus.OK);
        }
    }

    // Petición DELETE para eliminar comida por nombre
    // URL: http://localhost:9000/api/ComidasWs/eliminarPorNombre
    @DeleteMapping(path = "eliminarPorNombre")
    public ResponseEntity<String> eliminarComidaPorNombre(@RequestBody Map<String, String> request) {
        String nombre = request.get("nombre");
        try {
            String mensaje = comidaImp.eliminarComidaPorNombre(nombre);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Petición POST para buscar comida por fecha
    // URL: http://localhost:9000/api/ComidasWs/buscarPorFecha
    @PostMapping(path="buscarPorFecha")
    public ResponseEntity<?> buscarPorFecha(@RequestBody String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date parsedDate = sdf.parse(fecha);
            return ResponseEntity.ok(comidaImp.buscarPorFecha(parsedDate));
        } catch (Exception e) {
            return new ResponseEntity<>("Fecha inválida. Debe ser en formato yyyy-MM-dd.", HttpStatus.BAD_REQUEST);
        }
    }
}
