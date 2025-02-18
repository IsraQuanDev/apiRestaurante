
-- Eliminar la tabla existente (si es necesario)
DROP TABLE COMIDAS_MEX;

-- Crear la nueva tabla
CREATE TABLE COMIDAS_MEX(
    ID NUMBER PRIMARY KEY,
    NOMBRE NVARCHAR2(100) NOT NULL,
    PRECIO NUMBER NOT NULL,
    DESCRIPCION NVARCHAR2(80) NOT NULL,
    FECHA_VENTA DATE
);


-- Borrar la secuencia
DROP SEQUENCE SEC_ID_COMIDA;

-- Crear la secuencia para el campo ID
CREATE SEQUENCE SEC_ID_COMIDA
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER GENERA_AUT_COMIDA
BEFORE INSERT
ON COMIDAS_MEX
FOR EACH ROW
BEGIN
    SELECT SEC_ID_COMIDA.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/ 


-- Insertar un registro de ejemplo
INSERT INTO COMIDAS_MEX (ID, NOMBRE, PRECIO, DESCRIPCION, FECHA_VENTA) 
VALUES (1, 'Tacos al Pastor', 50.00, 'Deliciosos tacos con piña', TO_DATE('2025-01-27', 'YYYY-MM-DD'));

-- Confirmar la transacción
COMMIT;

-- Verificar los datos insertados
SELECT * FROM COMIDAS_MEX;





//DROP TABLE COMIDAS_MEX;


CREATE SEQUENCE SEC_ID_COMIDA
START WITH 1
INCREMENT BY 1;

// DROP SEQUENCE SEC_ID_COMIDA;


INSERT INTO COMIDAS_MEX (ID, NOMBRE, PRECIO, DESCRIPCION, FECHA_VENTA) 
VALUES (1, 'Tacos al Pastor', 50.00, 'Deliciosos tacos con piña', TO_DATE('2025-01-27', 'YYYY-MM-DD'));
COMMIT;

SELECT * FROM COMIDAS_MEX



