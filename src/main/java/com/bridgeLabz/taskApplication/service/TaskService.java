package com.bridgeLabz.taskApplication.service;

import com.bridgeLabz.taskApplication.dto.TaskDto;
import com.bridgeLabz.taskApplication.exception.TaskException;
import com.bridgeLabz.taskApplication.model.Task;
import com.bridgeLabz.taskApplication.repository.TaskRepo;
import com.bridgeLabz.taskApplication.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepo repo;
    @Autowired
    JwtToken token;

    public String addTask(TaskDto dto) {
        Task task = new Task(dto);
        task.setStatus("false");
        repo.save(task);
        return token.createToken(task.getId());
    }

    public List<Task> getTasks() {
        return repo.findAll();
    }

    public Task getById(String idToken) {
        int id = token.decode(idToken);
        return repo.findById(id).orElseThrow(() -> new TaskException(id + " this id not found"));
    }

    public Task update(String idToken, TaskDto dto) {
        int id = token.decode(idToken);
        Optional<Task> task = repo.findById(id);
        if (task.isEmpty()) {
            return repo.findById(id).orElseThrow(() -> new TaskException(id + " this id not found"));
        } else {
            task.get().setTitle(dto.getTitle());
            task.get().setDescription(dto.getDescription());
            task.get().setStartDate(dto.getStartDate());
            task.get().setEndDate(dto.getEndDate());
            return repo.save(task.get());
        }
    }

    public void delete(String idToken) {
        int id = token.decode(idToken);
        repo.deleteById(id);
    }

    public List<Task> status(String status) {
        return repo.status(status);
    }

    public List<Task> getTasksBySize(int first, int last) {
        List<Task> list = repo.findAll();
        if (list.size() < last) {
            last = list.size();
            return list.subList(first, last);
        }
        return list.subList(first, last);

    }

    public Task changeStatus(String idToken) {
        int id = token.decode(idToken);
        Task task = repo.findById(id).orElseThrow(() -> new TaskException(id + " this id not found"));
        if (task.getStatus().equals("false")) {
            task.setStatus("true");
        } else {
            task.setStatus("false");
        }
        repo.save(task);
        return task;
    }

    public List<Task> sortByEndDate() {
        return repo.sortByEndDate();
    }

    public List<Task> sortByStartDate() {
        return repo.sortByStartDate();
    }
}
