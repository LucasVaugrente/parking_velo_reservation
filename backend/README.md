# Parking Vélo - API

### A rendre le 8 janvier
### Oral le 9 janvier

Petite API Spring Boot (Java 21, Maven) pour gérer un parking de vélos : utilisateurs, vélos et réservations.

Arborescence importante
- `src/main/java/polytechdi4/parking_velo/`
    - `model/` : entités JPA (`Utilisateur`, `Velo`, `Reservation`, ...)
    - `dto/` : objets de transfert
    - `repository/` : interfaces Spring Data JPA
    - `service/` : logique métier (`ReservationService`, `UtilisateurService`, `VeloService`)
    - `controller/` : contrôleurs REST (`UtilisateurController`, `VeloController`, `ReservationController`)
    - `mapper/`, `exception/` : mappers et gestion des exceptions
- `src/main/resources/application.properties` : configuration
- `data/` : scripts SQL (`parking_structure.sql`, `parking_jdd.sql`)
- `src/main/java/polytechdi4/parking_velo/ParkingVeloApplication.java` : point d'entrée

Prérequis
- Java 21+
- Wrapper Maven fourni (`mvn`)
- MariaDB

## Base de données

```bash
mysql -u root -p
CREATE DATABASE parking_velo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT
mysql -u root -p parking_velo < data/parking_jdd.sql
```

## Configuration

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/parking_velo
spring.datasource.username=root
spring.datasource.password=...
spring.jpa.hibernate.ddl-auto=update
```

## Compilation et exécution

Construire :

```bash
mvn clean package
```

Lancer en développement :

```bash
mvn spring-boot:run
```

Ou exécuter le jar :

```bash
java -jar target/parking_velo-0.0.1-SNAPSHOT.jar
```

##  Endpoints principaux (préfixe `/api`)

- Utilisateurs (`UtilisateurController`)
    - `POST /api/utilisateurs` : créer
    - `GET  /api/utilisateurs` : lister
    - `GET  /api/utilisateurs/{id}` : récupérer
    - `PUT  /api/utilisateurs/{id}` : mettre à jour
    - `DELETE /api/utilisateurs/{id}` : supprimer

- Vélos (`VeloController`)
    - `POST /api/velos`
    - `GET  /api/velos`
    - `GET  /api/velos/{id}`
    - `PUT  /api/velos/{id}`
    - `DELETE /api/velos/{id}`

- Réservations (`ReservationController`)
    - `POST /api/reservations` : création (vérifie existence utilisateur/vélo, `fin` > `debut`, pas de chevauchement)
    - `GET  /api/reservations` : liste (filtres possibles)
    - `GET  /api/reservations/{id}`
    - `DELETE /api/reservations/{id}`

##  Paramètres de requête utiles pour les réservations
- `debut` et `fin` : filtrage par intervalle (format ISO 8601, ex. `2025-11-01T00:00:00`). Si fournis, `fin` doit être après `debut`.
    - Exemple : `/api/reservations?debut=2025-11-01T00:00:00&fin=2025-11-30T23:59:59`
- `utilisateurId` : filtrer par utilisateur
- `veloId` : filtrer par vélo
- Combinaisons : `/api/reservations?utilisateurId=1&debut=...&fin=...`

## Exemples curl
Créer un utilisateur :

```bash
curl -X POST -H "Content-Type: application/json" -d '{"nom":"Dupont","mail":"dupont@example.com"}' http://localhost:8080/api/utilisateurs
```

Créer une réservation :

```bash
curl -X POST -H "Content-Type: application/json" -d '{"utilisateurId":1,"veloId":2,"debut":"2025-11-01T09:00:00","fin":"2025-11-01T12:00:00"}' http://localhost:8080/api/reservations
```

Lister par période :

```bash
curl "http://localhost:8080/api/reservations?debut=2025-11-01T00:00:00&fin=2025-11-30T23:59:59"
```