# 📘 Project Setup Guide

This guide provides step-by-step instructions to start up the database service, populate initial data (only the first time), and run the Spring Boot backend.

---

## **🔹 1. Start the Database Service**
The project uses **PostgreSQL** as the database. To start the database container, run:

```sh
docker-compose up -d db
```

This will:
- Pull and start a **PostgreSQL 15** container.
- Expose **port 5432** for database connections.
- Store persistent data in a Docker volume (`pgdata`).

---

## **🔹 2. Populate the Database (First Time Only)**
If you're running the project **for the first time**, you need to initialize the database with seed data.  
Run the following command:

```sh
docker-compose up db_init
```

- This will wait for the database to be ready, then execute the `init.sql` script.
- Once completed, the container (`db_initializer`) will **automatically stop**.

🔹 **⚠️ Note:**
- You **only need to run `db_init` once**.
- If you need to re-run it, delete the volume using:

```sh
docker-compose down -v
```

---

## **🔹 3. Compile and Run the Spring Boot Application**
To start the backend service, follow these steps:

### **➤ Using Maven**
```sh
./mvnw clean install
java -jar target/your-app-name.jar
```

### **➤ Using an IDE (IntelliJ / VS Code / Eclipse)**
1. Open the project in your IDE.
2. Run the `main` method in the `Application` class.

The Spring Boot application will start and be available at:

```
http://localhost:8080
```

---

## **🔹 4. Access API Documentation (Swagger UI)**
Once the backend is running, you can explore the API using **Swagger UI**:

👉 **Swagger UI URL:**
```
http://localhost:8080/swagger-ui.html
```

---

## **🔹 5. Stopping and Cleaning Up**
To **stop** all containers, run:

```sh
docker-compose down
```

To **remove all containers and data**, run:

```sh
docker-compose down -v
```


