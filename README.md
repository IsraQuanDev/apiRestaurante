# apiRestaurante
Aquí tienes todo el contenido en un solo bloque para tu archivo `README.md`:

```markdown
# API Restaurante

Este es un proyecto de una API RESTful para gestionar un sistema de restaurante, donde se pueden gestionar comidas, consultar por ID, nombre, y fecha, además de realizar operaciones de creación, actualización y eliminación de registros. Está construida con Spring Boot.

## Endpoints Disponibles

### 1. Listar Comidas
- **URL:** `/api/ComidasWs/listar`
- **Método:** GET
- **Descripción:** Devuelve una lista de todas las comidas registradas.

### 2. Guardar Comida
- **URL:** `/api/ComidasWs/guardar`
- **Método:** POST
- **Descripción:** Guarda una nueva comida en la base de datos. Si el nombre de la comida ya existe, se devuelve un mensaje de error.
- **Cuerpo de la solicitud:**
```json
{
  "nombre": "Pizza",
  "descripcion": "Deliciosa pizza con queso y tomate",
  "precio": 12.99
}
```

### 3. Buscar Comida por ID
- **URL:** `/api/ComidasWs/buscarPorId`
- **Método:** POST
- **Descripción:** Busca una comida por su ID.
- **Cuerpo de la solicitud:**
```json
{
  "id": 1
}
```

### 4. Editar Comida
- **URL:** `/api/ComidasWs/editar`
- **Método:** POST
- **Descripción:** Edita los detalles de una comida existente.
- **Cuerpo de la solicitud:**
```json
{
  "id": 1,
  "nombre": "Pizza Margarita",
  "descripcion": "Pizza con tomate y albahaca",
  "precio": 14.99
}
```

### 5. Eliminar Comida por ID
- **URL:** `/api/ComidasWs/eliminarPorId`
- **Método:** POST
- **Descripción:** Elimina una comida por su ID.
- **Cuerpo de la solicitud:**
```json
{
  "id": 1
}
```

### 6. Buscar Comida por Nombre
- **URL:** `/api/ComidasWs/buscarPorNombre`
- **Método:** POST
- **Descripción:** Busca comidas que coincidan con un nombre dado.
- **Cuerpo de la solicitud:**
```json
{
  "nombre": "Pizza"
}
```

### 7. Eliminar Comida por Nombre
- **URL:** `/api/ComidasWs/eliminarPorNombre`
- **Método:** DELETE
- **Descripción:** Elimina una comida por su nombre.
- **Cuerpo de la solicitud:**
```json
{
  "nombre": "Pizza"
}
```

### 8. Buscar Comida por Fecha
- **URL:** `/api/ComidasWs/buscarPorFecha`
- **Método:** POST
- **Descripción:** Busca comidas según una fecha específica.
- **Cuerpo de la solicitud:**
```json
{
  "fecha": "2025-01-01"
}
```

## Requisitos

Para ejecutar este proyecto, necesitas tener instalado:

- **Java 17 o superior**
- **Spring Boot**
- **Maven**
- **MySQL** (u otro sistema de gestión de bases de datos compatible)

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/api-restaurante.git
```

2. Navega a la carpeta del proyecto:

```bash
cd api-restaurante
```

3. Configura las propiedades de conexión a la base de datos en `application.properties` o `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

4. Compila el proyecto:

```bash
mvn clean install
```

5. Ejecuta el proyecto:

```bash
mvn spring-boot:run
```

6. La API estará disponible en `http://localhost:9000`.

## Contribuciones

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b nueva-rama`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a tu rama (`git push origin nueva-rama`).
5. Crea un pull request.

## Licencia

Este proyecto está bajo la licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.
```

Este es el contenido completo para tu archivo `README.md`. Puedes copiarlo y pegarlo en el archivo correspondiente en tu repositorio de GitHub.
