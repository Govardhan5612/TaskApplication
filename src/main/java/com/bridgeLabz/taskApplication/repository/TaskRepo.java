package com.bridgeLabz.taskApplication.repository;

import com.bridgeLabz.taskApplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Query(value = "select * from task_details where status = :status", nativeQuery = true)
    List<Task> status(String status);

    @Query(value = "SELECT * FROM task_details ORDER BY end_date ASC;", nativeQuery = true)
    List<Task> sortByEndDate();

    @Query(value = "SELECT * FROM task_details ORDER BY start_date ASC;", nativeQuery = true)
    List<Task> sortByStartDate();
}
