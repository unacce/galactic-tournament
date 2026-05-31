# Galactic Tournament

Aplicación web desarrollada con Spring Boot y Angular para gestionar el Gran Torneo Galáctico.

## Descripción

Cada 500 años, las especies más poderosas del universo participan en el Gran Torneo Galáctico.

La aplicación permite:

* Registrar especies galácticas.
* Iniciar combates entre especies.
* Consultar el historial de combates.
* Visualizar el ranking de especies según sus victorias.
* Ejecutar combates aleatorios (bonus).

---

## Tecnologías Utilizadas

### Backend

* Java 21
* Spring Boot 3.5
* Maven
* Jakarta Validation

### Frontend

* Angular 21
* TypeScript
* RxJS

### Testing

* JUnit 5
* Spring Boot Test
* MockMvc

### Contenerización

* Docker
* Docker Compose

---

## Arquitectura

El backend sigue una arquitectura por capas:

Controller
↓
Service
↓
Repository
↓
Storage In-Memory

Principales componentes:

* Controllers: exponen los endpoints REST.
* Services: contienen la lógica de negocio.
* Repositories: gestionan la persistencia en memoria.
* DTOs: contratos de entrada y salida.
* Exceptions: manejo centralizado de errores.

---

## Funcionalidades

### Gestión de Especies

Permite:

* Registrar especies.
* Consultar especies registradas.

Cada especie contiene:

* Nombre
* Nivel de poder
* Habilidad especial
* Número de victorias

---

### Gestión de Combates

Permite:

* Crear combates manuales.
* Crear combates aleatorios.

Reglas:

1. Gana la especie con mayor nivel de poder.
2. En caso de empate:

   * Gana la especie cuyo nombre aparezca primero alfabéticamente.
3. El resultado se almacena en el historial.

---

### Historial de Combates

Permite consultar todos los combates realizados.

Información almacenada:

* Id del combate
* Especie participante 1
* Especie participante 2
* Ganador
* Fecha

---

### Ranking

Permite visualizar las especies ordenadas por:

* Cantidad de victorias (descendente)

---

## Requisitos

Instalar previamente:

* Java 21
* Maven 3.9+
* Node.js 22+
* Angular CLI
* Docker (opcional)

---

## Instalación

### Clonar repositorio

```bash
git clone https://github.com/ebchalacan92/galactic-tournament.git

cd galactic-tournament
```

---

## Ejecución Backend

Ingresar al proyecto backend:

```bash
cd backend/galactic-tournament-api
```

Compilar:

```bash
mvn clean install
```

Ejecutar:

```bash
mvn spring-boot:run
```

Backend disponible en:

```text
http://localhost:8080
```

---

## Ejecución Frontend

Ingresar al proyecto frontend:

```bash
cd frontend/galactic-tournament-ui
```

Instalar dependencias:

```bash
npm install
```

Ejecutar:

```bash
ng serve
```

Frontend disponible en:

```text
http://localhost:4200
```

---

## Docker

### Construir contenedores

```bash
docker-compose up --build
```

### Backend

http://localhost:8080

### Frontend

http://localhost:4200

---

## Endpoints

### Species

Crear especie

```http
POST /api/species
```

Listar especies

```http
GET /api/species
```

Consultar ranking

```http
GET /api/species/ranking
```

---

### Battles

Crear combate

```http
POST /api/battles
```

Crear combate aleatorio

```http
POST /api/battles/random
```

Consultar historial

```http
GET /api/battles
```

---

## Pruebas

Ejecutar:

```bash
mvn test
```

Pruebas implementadas:

### Unitarias

* SpeciesServiceTest
* BattleServiceTest

### Integración

* shouldCreateSpeciesSuccessfully
* shouldReturnBadRequestWhenNameIsEmpty
* shouldReturnSpeciesList
* shouldCreateBattleSuccessfully

---

## Autor

Bryan Chalacan
