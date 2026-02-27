# 🏪 Sistema de Gestión para Distribuidora de Alimentos – Backend

Backend de un sistema de gestión web desarrollado para una distribuidora de alimentos.

Este proyecto fue realizado en el marco de la materia **Práctica Profesionalizante** de la carrera *Tecnicatura en Desarrollo de Software*, a partir de un relevamiento de requerimientos obtenidos mediante una **entrevista real con la empresa**, simulando un entorno profesional de desarrollo.

> ⚠️ Este repositorio contiene únicamente el backend (API REST).  
---

## 📌 Objetivo del Proyecto

Diseñar e implementar una API REST segura y escalable para la gestión integral de una distribuidora de alimentos, permitiendo:

- Gestión de productos  
- Gestión de clientes  
- Gestión de pedidos y facturación  
- Administración de usuarios  
- Control de acceso y autenticación  

Se priorizó la aplicación de buenas prácticas, separación de responsabilidades y seguridad en el manejo de datos.

---

## 🧱 Arquitectura

El backend sigue una arquitectura en capas:

- **Controller** → Exposición de endpoints REST  
- **Service** → Lógica de negocio  
- **Repository** → Acceso a datos con JPA  
- **Entity / DTO** → Modelado y transferencia de datos  
- **Security** → Configuración de autenticación y autorización con JWT  

Se aplicó una clara separación de responsabilidades para mejorar mantenibilidad y escalabilidad.

---

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- Spring Web (REST API)
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- BCrypt (encriptación de contraseñas)
- Hibernate
- MySQL (entorno persistente)
- Lombok
- Jakarta Validation
- Maven

---

## 🔐 Seguridad

El sistema implementa:

- Autenticación basada en **JWT**
- Encriptación de contraseñas mediante **BCrypt**
- Filtros personalizados de seguridad
- Control de acceso basado en roles

Se protegen los endpoints sensibles y se evita el almacenamiento de contraseñas en texto plano.

---

## 🗄️ Base de Datos

- **MySQL** → Base de datos relacional para entorno persistente  

La configuración se realiza desde el archivo `application.properties`.

---

## ▶️ Ejecución del Proyecto

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/distri.git
cd distri
````

### 2️⃣ Compilar el proyecto

```bash
mvn clean install
```

## 👩‍💻 Trabajo en Equipo

Proyecto desarrollado bajo modalidad colaborativa:

* Relevamiento de requerimientos reales
* Análisis y modelado del sistema
* Diseño de base de datos
* Desarrollo backend y frontend por separado
* Integración final del sistema

Se trabajó simulando un entorno profesional real.

---

## 🎓 Contexto Académico
El objetivo fue aplicar conocimientos técnicos en un proyecto basado en necesidades reales, integrando:

* Seguridad con JWT
* Persistencia con JPA
* Arquitectura en capas
* Buenas prácticas de desarrollo backend

---

## 📄 Estado del Proyecto

* ✔️ API funcional
* ✔️ Autenticación implementada
* ✔️ Persistencia configurada
* ✔️ Integrado con frontend

Desarrollado como proyecto académico integrador para la carrera de Tecnicatura en Desarrollo de Software.

