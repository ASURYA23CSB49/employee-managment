# Employee Management System

A full-stack Three-Tier Employee Management System built with HTML/CSS/JS (Frontend), Spring Boot (Backend), and MySQL (Database).

## Project Structure

```
employee-management/
├── backend/                  # Spring Boot REST API
│   ├── src/main/java/com/emp/management/
│   │   ├── controller/       # REST Controllers
│   │   ├── service/          # Business Logic
│   │   ├── repository/       # Data Access Layer
│   │   ├── entity/           # JPA Entities
│   │   ├── dto/              # Data Transfer Objects
│   │   └── exception/        # Custom Exceptions & Global Handler
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── Dockerfile
│   └── pom.xml
├── frontend/                 # Static HTML/CSS/JS
│   ├── index.html            # Login
│   ├── css/styles.css
│   ├── js/
│   │   ├── app.js            # API client + shared helpers
│   │   └── layout.js         # Sidebar/topbar renderer
│   └── pages/
│       ├── register.html
│       ├── dashboard.html
│       ├── employees.html
│       ├── add-employee.html
│       ├── edit-employee.html
│       └── view-employee.html
├── database/
│   └── schema.sql            # MySQL schema + sample data
├── docker-compose.yml
└── README.md
```

## API Endpoints

| Method | Endpoint                        | Description              |
|--------|---------------------------------|--------------------------|
| GET    | /api/employees                  | Get all employees        |
| GET    | /api/employees?search=query     | Search employees         |
| GET    | /api/employees/{id}             | Get employee by ID       |
| POST   | /api/employees                  | Create employee          |
| PUT    | /api/employees/{id}             | Update employee          |
| DELETE | /api/employees/{id}             | Delete employee          |
| GET    | /api/employees/dashboard        | Dashboard statistics     |

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Docker & Docker Compose (optional)

---

## Option 1 — Run with Docker (Recommended)

Each service has its own `Dockerfile` and `docker-compose.yml`. Start them in order:

### Step 1 — Create the shared network (once)
```bash
docker network create emp_network
```

### Step 2 — Start MySQL
```bash
cd database
docker-compose up -d --build
```

### Step 3 — Start Backend
```bash
cd backend
docker-compose up -d --build
```

### Step 4 — Start Frontend
```bash
cd frontend
docker-compose up -d --build
```

| Service  | URL                    |
|----------|------------------------|
| Frontend | http://localhost       |
| Backend  | http://localhost:8080  |
| MySQL    | localhost:3306         |

---

## Option 2 — Run Manually

### 1. Set up MySQL

```sql
-- Connect to MySQL and run:
source database/schema.sql;
```

Or import it via MySQL Workbench / DBeaver.

### 2. Configure Database (if needed)

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Build & Run Backend

```bash
cd backend
mvn clean package -DskipTests
java -jar target/employee-management-1.0.0.jar
```

Backend runs at: http://localhost:8080

### 4. Open Frontend

Open `frontend/index.html` in your browser (use Live Server in VS Code for best experience).

---

## Demo Login

| Field    | Value              |
|----------|--------------------|
| Email    | admin@company.com  |
| Password | admin123           |

---

## Features

- Employee Login & Registration (client-side auth)
- Dashboard with total employees, salary stats, department chart
- Employee List with search
- Add / Edit / View / Delete employees
- Input validation (frontend + backend)
- Global exception handling
- Responsive UI (mobile-friendly)
