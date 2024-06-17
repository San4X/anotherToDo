package com.black.todo.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestParam String description,
                                        @RequestParam Date dueDate,
                                        @RequestParam int priority,
                                        @RequestParam Integer taskListId) {
        return ResponseEntity.ok(taskService.addTask(description, dueDate, priority, taskListId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam Integer taskListId) {
        return ResponseEntity.ok(taskService.getTasks(taskListId));
    }

    @PostMapping("/{taskId}/complete")
    public ResponseEntity<Void> markTaskAsComplete(@PathVariable Integer taskId) {
        taskService.markTaskAsComplete(taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/reminder")
    public ResponseEntity<Void> setTaskReminder(@PathVariable Integer taskId, @RequestParam Date reminder) {
        taskService.setTaskReminder(taskId, reminder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

