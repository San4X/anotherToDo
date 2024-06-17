package com.black.todo.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private String description;
    private LocalDate dueDate;
    private Integer priority;
    private LocalDateTime reminder;
    private Integer taskListId;
    private Integer taskId;
}

