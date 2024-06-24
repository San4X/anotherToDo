package com.black.todo.todo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


@Operation(
        description = "Add new task"
)
    @PostMapping
    public ResponseEntity<Task> addTask(
            @RequestBody TaskRequest request
    ) {
        Task task = taskService.addTask(request);
        return ResponseEntity.ok(task);
    }


    @Operation(
            description = "Get all tasks from certain task list"
    )
    @GetMapping("/{taskListId}")
    public ResponseEntity<List<Task>> getTasks(
            @PathVariable Integer taskListId
    ) {
        List<Task> tasks = taskService.getTasks(taskListId);
        return ResponseEntity.ok(tasks);
    }


    @Operation(
            description = "Mark task as complete"
    )
    @PatchMapping("/{taskId}/marktask/{complete}")
    public ResponseEntity<Task> markTask(@PathVariable Integer taskId,
                                         @PathVariable Boolean complete) {
        Task task = taskService.markTask(taskId, complete);
        return ResponseEntity.ok(task);
    }


    @Operation(
            description = "Set reminder in format year-month-dayThour:minute:second"
    )
    @PatchMapping("/reminder")
    public ResponseEntity<Task> setReminder(
            @RequestBody TaskRequest request
    ) {
        Task task = taskService.setReminder(request);
        return ResponseEntity.ok(task);
    }


    @Operation(
            description = "Delete task"
    )
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

