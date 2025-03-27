# Spring-Task-Manager

A **Spring Boot** and **SQL**-based web application for managing tasks and projects, with an automated **deadline scheduler** to check for expired deadlines.  

---

## **📌 Features**  
✔ **Task & Project Management** – Create, update, and track tasks.  
✔ **User Authentication** – Secure login and role-based access.  
✔ **Deadline Scheduler** – Automated background job checks for expired deadlines and sends alerts.  
✔ **RESTful API** – Full CRUD operations for tasks and projects.  
✔ **Database Integration** – Uses **SQL ** for data persistence.  

---

## **🛠 Technologies Used**  
- **Backend**: Spring Boot (Java)  
- **Database**: SQL 
- **Scheduling**: Spring Scheduler (`@Scheduled`)  
- **Frontend**: Thymeleaf

---

## **🚀 Getting Started**  

### **Prerequisites**  
- Java 17+  
- Maven  
- SQL
- XAMPP

### **Setup & Run**  
1. **Clone the repository**:  
   ```sh
   git clone https://github.com/TallOrder99/spring-Task-Manager.git
   cd spring-Task-Manager
   ```  

2. **Configure the database**:  
   - Update `application.properties` (or `application.yml`):  
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/taskmanagementdb
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```  

3. **Run the application**:  
   ```sh
   mvn spring-boot:run
   ```  
   - The app will start at: `http://localhost:8086`

4. **Access APIs**:  
   - Swagger UI (if enabled): `http://localhost:8080/swagger-ui.html`  
   - REST Endpoints:  
     - `GET /projects/viewprojectandtask` – List all tasks  
     - `POST /newproject` – Create a new project
     - `PUT /projects/edit/#id` – Update a task  
     - `DELETE /projects/delete/#id` – Delete a task  

---

## **⏰ Deadline Scheduler**  
The system includes a **background scheduler** that checks for expired deadlines daily (configurable).  

### **How It Works**  
- Uses Spring’s `@Scheduled` annotation:  
  ```java
  @Scheduled(cron = "0 0 0 * * ?") // Runs every midnight
  public void checkDeadlines() {
      List<Task> expiredTasks = taskRepository.findByDeadlineBefore(LocalDate.now());
      expiredTasks.forEach(task -> notifyUser(task));
  }
  ``` 
