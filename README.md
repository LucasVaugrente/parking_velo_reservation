## Parking Velo Reservation

This repository is based on two differents ones which are :

- [Frontend](https://github.com/LucasVaugrente/capgemini_parking_velo_frontend "Frontend")
- [Backend](https://github.com/LucasVaugrente/capgemini_parking_velo_backend "Backendb")

## Docker installation

### Prerequisites
- Docker
- Docker Compose

### Run with Docker

From the repository root, start the application with:

```bash
docker compose up --build
```

This command will build and start both the backend and frontend services defined in `docker-compose.yml`.

### Accessing the application

Once the containers are running, open the frontend at:

```text
http://localhost:4200
```

Use the following login credentials:

- Email: `dupont@example.com`
- Password: `monSuperMDP`

### Stop and remove containers

```bash
docker compose down
```

# 🙎‍♂️ Contributors
* [Lucas Vaugrente](https://github.com/LucasVaugrente "Compte GitHub")
* [Salma Mansouri](https://github.com/Salma-msr "Compte GitHub")
* [Imane Abdou](https://github.com/VimaneAb "Compte GitHub")