package com.bridgeLabz.taskApplication.controller;

import com.bridgeLabz.taskApplication.dto.ResponseDto;
import com.bridgeLabz.taskApplication.dto.TaskDto;
import com.bridgeLabz.taskApplication.model.Task;
import com.bridgeLabz.taskApplication.service.TaskService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addTask(@Valid @RequestBody TaskDto dto) {
        ResponseDto responseDto = new ResponseDto("Adding task details", service.addTask(dto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAll() {
        ResponseDto responseDto = new ResponseDto("All task details ", service.getTasks());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<ResponseDto> getById(@RequestHeader String token) {
        Task task = service.getById(token);
        ResponseDto dto = new ResponseDto("Task Details", task);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestHeader String token, @Valid @RequestBody TaskDto dto) {
        Task task = service.update(token, dto);
        ResponseDto responseDto = new ResponseDto("Updated Task details", task);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> delete(@RequestHeader String token) {
        service.delete(token);
        ResponseDto dto = new ResponseDto("Id is deleted", "");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/inProgress")
    public ResponseEntity<ResponseDto> inProgress() {
        ResponseDto dto = new ResponseDto("In progress Tasks ", service.status("false"));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/completeProgress")
    public ResponseEntity<ResponseDto> completeProgress() {
        ResponseDto dto = new ResponseDto("Complete progress Tasks ", service.status("true"));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<ResponseDto> paging(@RequestHeader int first, @RequestHeader int last) {
        ResponseDto dto = new ResponseDto("Tasks ", service.getTasksBySize(first, last));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseDto> changeStatus(@RequestHeader String status) {
        ResponseDto dto = new ResponseDto("Tasks ", service.changeStatus(status));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/startDate")
    public ResponseEntity<ResponseDto> sortByStartDate() {
        ResponseDto responseDto = new ResponseDto("Sort By Start Date", service.sortByStartDate());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/endDate")
    public ResponseEntity<ResponseDto> sortByEndDate() {
        ResponseDto responseDto = new ResponseDto("Sort By End Date ", service.sortByEndDate());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
