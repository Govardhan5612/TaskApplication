package com.bridgeLabz.taskApplication.exception;

import com.bridgeLabz.taskApplication.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class TaskControllerException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> argException(MethodArgumentNotValidException exception) {
        List<ObjectError> list = exception.getBindingResult().getAllErrors();
        List<String> defaultErrors = list.stream().map(errors -> errors.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto dto = new ResponseDto("Error messages", defaultErrors);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ResponseDto> idException(TaskException exception) {
        ResponseDto dto = new ResponseDto("Id not found", exception.getMessage().toUpperCase());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
