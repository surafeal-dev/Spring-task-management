# ✅ Task-Manager (Spring)

**Task-Manager** is a **Spring Boot & MySQL-based** web application designed for efficient task and project management, featuring automated deadline tracking and notifications.

---

## 📌 Features

- **📝 Task & Project Management** – Create, update, and monitor tasks and projects.  
- **🔐 User Authentication** – Secure login with role-based access control.  
- **⏰ Automated Deadline Scheduler** – Background job to check for expired deadlines and send alerts.  
- **📡 RESTful API** – Full CRUD operations for managing tasks and projects.  
- **💾 Database Integration** – Uses MySQL for data storage and persistence.  

---

## 🛠 Technology Stack

- **Backend:** Spring Boot (Java)  
- **Database:** MySQL  
- **Scheduling:** Spring Scheduler (@Scheduled)  
- **Frontend:** Thymeleaf  

---

## ✅ Prerequisites

- **Java 17+**
- **Maven**
- **MySQL (XAMPP optional)**

---

## ⚙️ Installation & Setup

### 📥 1. Clone the Repository
```sh
git clone https://github.com/TallOrder99/Task-Manager-Spring.git
cd Task-Manager-Spring
```

### 🛠 2. Configure the Database
Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskmanagementdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 🚀 3. Run the Application
```sh
mvn spring-boot:run
```
📌 The application will be available at: **http://localhost:8086**

---

## 🌐 API Endpoints

- **GET** `/projects/viewprojectandtask` – Retrieve all tasks and projects.
- **POST** `/newproject` – Create a new project.
- **PUT** `/projects/edit/{id}` – Update a specific task.
- **DELETE** `/projects/delete/{id}` – Remove a task.

---

## ⏳ Deadline Scheduler

The system includes an automated scheduler that checks for expired deadlines daily and sends notifications.

### 🔧 How It Works

Spring’s `@Scheduled` annotation triggers background checks:
```java
@Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
public void checkDeadlines() {
    List<Task> expiredTasks = taskRepository.findByDeadlineBefore(LocalDate.now());
    expiredTasks.forEach(task -> notifyUser(task));
}
```

---

## 📜 License

This project is licensed under the **MIT License**.

---

### 💡 Built with ❤️ using Spring Boot & MySQL 🚀
