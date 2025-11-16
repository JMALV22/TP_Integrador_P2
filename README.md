# 💻 Trabajo Práctico Integrador - Programación I

# Sistema de Gestión de Pacientes y Historias Clínicas
Trabajo Práctico Integrador – Programación II

---

## 👥 Estudiantes

- **Nombre:** Luciano Andrelo  
- **Nombre:** Nicolás Azcuy
- **Nombre:** Jose Matias Alvarez
- **Nombre:** Jeremias Apiani
- **Comisión:** M2025-17

---

## 📌 Descripción del proyecto
Aplicación de consola desarrollada en Java que gestiona pacientes y sus historias clínicas,
implementando una relación **1→1 unidireccional** (Paciente → Historia Clínica).  
El proyecto sigue una arquitectura por capas (config / dao / service / main) y utiliza
**MySQL + JDBC** con manejo de **baja lógica** y **transacciones (commit / rollback)**.

Este trabajo corresponde al TPI de Programación II (Tecnicatura Universitaria en Programación).

---

## ✨ Funcionalidades principales
- Alta, baja lógica, actualización y consulta de Pacientes
- Alta, baja lógica, actualización y consulta de Historias Clínicas
- Asociación 1→1 entre Paciente e Historia (un Paciente puede tener 0 o 1 Historia)
- Uso de `PreparedStatement` para evitar SQL Injection
- Servicios con control transaccional (`setAutoCommit(false)`)
- Menú interactivo por consola
- Soft delete (los datos NO se eliminan físicamente)

---

## 🧱 Arquitectura del Proyecto

src/  
├── **config/** → Manejo de conexión JDBC  
├── **dao/** → Interfaces DAO + Implementaciones JDBC  
├── **entities/** → Clases del modelo + enum GrupoSanguineo  
├── **service/** → Lógica de negocio + transacciones  
└── **main/** → Menú y ejecución del sistema


### Patrón utilizado:
- **DAO (Data Access Object)**
- **Services** para manejar reglas de negocio
- **AppMenu** para interacción con el usuario

---

## 🗄️ Base de Datos

El script SQL se encuentra en el archivo:  
`pacienteHistoriaClinica.sql`

Incluye:
- Creación del esquema
- Tablas `paciente` y `historia_clinica` (1→1)
- Población automática de 10.000 pacientes y sus historias
- Implementación de **baja lógica**
- Foreign key con `ON DELETE CASCADE` y `UNIQUE` en `id_paciente`

---

## 🚀 Requisitos

- Java 21 (o superior)
- MySQL Server / XAMPP / WAMP
- Conector JDBC (mysql-connector)
- IntelliJ IDEA o NetBeans

---

## ⚙️ Configuración del proyecto

### 1. Crear la base de datos

Ejecutar el script SQL:

```sql
SOURCE pacienteHistoriaClinica.sql;
```

### 2. Configurar conexión en `ConexionDB.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/pacienteHistoriaClinica";
private static final String USER = "root";
private static final String PASS = "";
```

## ▶️ Ejecución del sistema

#### Para probar la conexión:
```
config.ProbarConexion
```

#### Para ejecutar la aplicación:
```
main.AppMenu
```

