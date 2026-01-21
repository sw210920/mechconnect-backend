# MechConnect - Backend (Spring Boot)

MechConnect is a vehicle service booking platform where customers can find mechanics by location and specialization, and book services either at their doorstep or by visiting the mechanic shop.

This repository contains the **Backend REST API** built using **Java + Spring Boot**.

---

## 🔗 Repository
**Backend Repo (SSH):**
```bash
git@github.com:sw210920/mechconnect-backend.git
```

---

## 🚀 Features
- Customer Registration & Login
- Mechanic Registration & Listing
- Search mechanics by:
  - Location (City/Area)
  - Specialization (Bike / Car / General)
- Service Booking system:
  - Doorstep Service
  - Visit Mechanic Shop
- Forgot Password using OTP (Email based)
- REST APIs for frontend integration
- MySQL database support

---

## 🛠 Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman (API Testing)

---

## 📂 Project Structure (Typical)
```
src/main/java/com/mechconnect
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 └── config
```

---

## ⚙️ Database Configuration

### ✅ 1) Create MySQL Database
Run this in MySQL:
```sql
CREATE DATABASE mechconnect;
```

### ✅ 2) Configure `application.properties`
Path:
```
src/main/resources/application.properties
```

Example:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mechconnect
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

---

## ▶️ Run the Project

### ✅ 1) Clone the Repository
```bash
git clone git@github.com:sw210920/mechconnect-backend.git
cd mechconnect-backend
```

### ✅ 2) Run Using Maven
```bash
mvn spring-boot:run
```

Backend will start on:
```text
http://localhost:8080
```

---

## 🔥 Sample API Endpoints (Example)

### ✅ Customer APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/customers/register` | Register customer |
| POST | `/customers/login` | Customer login |
| POST | `/customers/forgot-password/find-user` | Find user for forgot password |
| POST | `/customers/forgot-password/send-otp` | Send OTP |
| POST | `/customers/forgot-password/verify-otp` | Verify OTP |
| POST | `/customers/forgot-password/reset-password` | Reset password |

---

### ✅ Mechanic APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/mechanics/register` | Register mechanic |
| GET | `/mechanics/all` | Get all mechanics |
| GET | `/mechanics/nearby?serviceLocation=Pune&specialization=Car` | Search nearby mechanics |

---

### ✅ Booking APIs
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/bookings/create` | Create a booking |
| GET | `/bookings/customer/{customerId}` | Customer booking history |
| GET | `/bookings/mechanic/{mechanicId}` | Mechanic booking list |

> Note: Endpoints can vary depending on your final backend implementation.

---

## ✅ Future Enhancements
- Admin Panel (Manage users, mechanics, bookings)
- JWT Authentication
- Payment Gateway Integration
- Reviews & Ratings for mechanics
- Booking tracking (Pending → Accepted → Completed)

---

## 👨‍💻 Developed By
**Shubham Wani**  
Java Full Stack Developer  
LinkedIn: linkedin.com/in/shubham-wani-91074a213

---

## 📜 License
This project is created for learning and portfolio purposes.
