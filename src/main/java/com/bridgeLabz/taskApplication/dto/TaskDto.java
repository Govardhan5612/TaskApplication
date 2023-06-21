package com.bridgeLabz.taskApplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {

    @NotBlank(message = "Not give Blank values")
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,}[\\s]{0,1}[A-Z]{0,1}[a-z]{0,}[\\s]{0,1}[A-Z]{0,1}[a-z]{0,}", message = "Invalid Task name")
    private String title;
    @NotEmpty(message = "Not give empty values")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$", message = "Enter minimum three characters")
    private String description;
    private String status;
    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "This field is not null")
    @PastOrPresent(message = "Enter past date")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "This field is not null")
    @FutureOrPresent(message = "Enter future date")
    private LocalDate endDate;

}
