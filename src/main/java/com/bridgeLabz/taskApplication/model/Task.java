package com.bridgeLabz.taskApplication.model;

import com.bridgeLabz.taskApplication.dto.TaskDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "TaskDetails")
@NoArgsConstructor
public class Task {
    /*
    Each task should have an ID, title, description, and status (e.g., "todo," "in progress," "completed").
The API should allow the following operations:
Create a new task.
Retrieve a specific task by its ID.
Retrieve all tasks.
Update an existing task (title, description, and status).
Delete a task by its ID.
Guidelines:

Use Spring Boot and Spring Data JPA for building the API.
Design appropriate entity classes and repositories to interact with the database.
Implement validation for the task fields (e.g., title and description should not be empty).
Handle exceptions gracefully and return proper error responses when necessary.
Test the API using a tool like Postman or cURL.
Bonus: Implement pagination and sorting for retrieving all tasks.

You can start by creating the necessary entity class, repository interface, and controller class.
 Implement the required CRUD operations, handle validation using annotations like @NotEmpty or @NotNull,
 and handle exceptions using @ExceptionHandler.

 If you want an additional challenge, you can try implementing pagination and sorting to retrieve
 tasks in a paginated manner and sort them based on specific criteria.
     */
    @Id
    @GeneratedValue
    int id;
    private String title, description, status;
    private LocalDate startDate, endDate;

    public Task(TaskDto dto) {
        title = dto.getTitle();
        description = dto.getDescription();
        status = dto.getStatus();
        startDate = dto.getStartDate();
        endDate = dto.getEndDate();
    }

}
