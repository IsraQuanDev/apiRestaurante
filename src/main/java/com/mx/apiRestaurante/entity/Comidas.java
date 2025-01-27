package com.mx.apiRestaurante.entity;

import java.io.Serializable;
import java.util.Date;	

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
--- apiRestaurante ----

CREATE TABLE COMIDAS_MEX(
ID NUMBER PRIMARY KEY,
NOMBRE NVARCHAR2(100) NOT NULL,
PRECIO NUMBER NOT NULL,
DESCRIPCION NVARCHAR2(80) NOT NULL,
FECHA_VENTA DATE
); 

*/

@Entity  // Anotación para indicar que esta clase es una entidad que se mapea a una tabla en la base de datos.
@Table(name = "COMIDAS_MEX") // Especifica el nombre de la tabla en la base de datos.
@Data  // Lombok genera automáticamente los métodos getter, setter, toString, equals y hashCode para esta clase.
public class Comidas implements Serializable {

    private static final long serialVersionUID = 1L; // Identificador único para la serialización de la clase.
    
    @Id  // Indica que este campo es la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define la estrategia para generar el valor de la clave primaria (autoincrementable).
    @Column(name = "ID")  // Mapea este campo a la columna 'ID' de la tabla.
    private Long id;  // Representa el identificador único de la comida en la base de datos.
    
    @Column(name = "NOMBRE")  // Mapea este campo a la columna 'NOMBRE' de la tabla.
    private String nombre;  // Representa el nombre de la comida.
    
    @Column(name = "PRECIO")  // Mapea este campo a la columna 'PRECIO' de la tabla.
    private Float precio;  // Representa el precio de la comida.
    
    @Column(name = "DESCRIPCION")  // Mapea este campo a la columna 'DESCRIPCION' de la tabla.
    private String descripcion;  // Representa una breve descripción de la comida.
    
    @Column(name = "FECHA_VENTA")  // Mapea este campo a la columna 'FECHA_VENTA' de la tabla.
    private Date fechaVenta;  // Representa la fecha de venta de la comida.

}
