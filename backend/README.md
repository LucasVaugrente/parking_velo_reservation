# Application Vélo - API Spring Boot

## Database (Parking Vélo)

You have to have MariaDB installed and running.

```bash
mysql -u root -p
CREATE DATABASE parking_velo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT
mysql -u root -p parking_velo < data/parking_jdd.sql
```

## Configuration

You have to change the username and password in `src/main/resources/application.properties` with yours :

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/parking_velo
spring.datasource.username=`username`
spring.datasource.password=`password`
spring.jpa.hibernate.ddl-auto=update
```

## Build and Run 

Build the project :

```bash
mvn clean package
```

Run the API :

```bash
mvn spring-boot:run
```

## Some useful informations 

### Endpoints (`/api`)

- Users (`UtilisateurController`)
    - `POST /api/utilisateurs`
    - `GET  /api/utilisateurs`
    - `GET  /api/utilisateurs/{id}`
    - `PUT  /api/utilisateurs/{id}`
    - `DELETE /api/utilisateurs/{id}`

- Bike (`VeloController`)
    - `POST /api/velos`
    - `GET  /api/velos`
    - `GET  /api/velos/{id}`
    - `PUT  /api/velos/{id}`
    - `DELETE /api/velos/{id}`

- Reservations (`ReservationController`)
    - `POST /api/reservations`
    - `GET  /api/reservations`
    - `GET  /api/reservations/{id}`
    - `PUT  /api/reservations/{id}`
    - `DELETE /api/reservations/{id}`

### Examples Request API (with curl)

Create a user :

```bash
curl -X POST -H "Content-Type: application/json" -d '{"nom":"Dupont","prenom":"Jean","mail":"dupont@example.com","username":"dupontj","password":"monSuperMDP"}' http://localhost:8080/api/utilisateurs
```

Create a reservation :

```bash
curl -X POST -H "Content-Type: application/json" -d '{"utilisateurId":1,"veloId":2,"debut":"2025-11-01T09:00:00","fin":"2025-11-01T12:00:00"}' http://localhost:8080/api/reservations
```

# 🙎‍♂️ Contributors
* [Lucas Vaugrente](https://github.com/LucasVaugrente "Compte GitHub")
* [Salma Mansouri](https://github.com/Salma-msr "Compte GitHub")
* [Imane Abdou](https://github.com/VimaneAb "Compte GitHub")

