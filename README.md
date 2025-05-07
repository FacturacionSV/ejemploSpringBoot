# Sistema de Facturación El Salvador

Sistema de facturación electrónica desarrollado en Spring Boot para El Salvador.

## Características

- Generación de facturas electrónicas
- Integración con API de facturación
- Interfaz web con Thymeleaf y Bootstrap
- Procesamiento de documentos DTE
- Validación de datos según esquema JSON requerido

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Thymeleaf
- Bootstrap 5
- JavaScript
- Maven

## Requisitos

- JDK 17 o superior
- Maven
- Conexión a internet para la API de facturación

## Configuración

1. Clonar el repositorio:
```bash
git clone https://github.com/FacturacionSV/ejemploSpringBoot.git
```

2. Configurar las variables de entorno en `application.properties`:
```properties
api.url=https://tu-api-url
```

3. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── facturacion/
│   │           ├── controller/
│   │           ├── model/
│   │           └── service/
│   └── resources/
│       ├── static/
│       └── templates/
└── test/
```

## Licencia

Este proyecto está bajo la Licencia MIT. 