package com.black.todo.todo;

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

//    @PostMapping
//    public ResponseEntity<Task> addTask(@RequestParam String description,
//                                        @RequestParam Date dueDate,
//                                        @RequestParam int priority,
//                                        @RequestParam Integer taskListId) {
//        return ResponseEntity.ok(taskService.addTask(description, dueDate, priority, taskListId));
//    }
    @PostMapping
    public ResponseEntity<Task> addTask(
            @RequestBody TaskRequest request
    ) {
        Task task = taskService.addTask(request);
        return ResponseEntity.ok(task);
    }

//    @GetMapping
//    public ResponseEntity<List<Task>> getTasks(@RequestParam Integer taskListId) {
//        return ResponseEntity.ok(taskService.getTasks(taskListId));
//    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<List<Task>> getTasks(
            @PathVariable Integer taskListId
    ) {
        List<Task> tasks = taskService.getTasks(taskListId);
        return ResponseEntity.ok(tasks);
    }


//    @PostMapping("/{taskId}/complete")
//    public ResponseEntity<Void> markTaskAsComplete(@PathVariable Integer taskId) {
//        taskService.markTaskAsComplete(taskId);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> markAsComplete(@PathVariable Integer taskId) {
        Task task = taskService.markAsComplete(taskId);
        return ResponseEntity.ok(task);
    }

//    @PostMapping("/{taskId}/reminder")
//    public ResponseEntity<Void> setTaskReminder(@PathVariable Integer taskId, @RequestParam Date reminder) {
//        taskService.setTaskReminder(taskId, reminder);
//        return ResponseEntity.noContent().build();
//    }

//    @PutMapping("/{taskId}/reminder")
//    public ResponseEntity<Task> setReminder(
//            @PathVariable Integer taskId,
//            @RequestParam LocalDateTime reminder
//    ) {
//        Task task = taskService.setReminder(taskId, reminder);
//        return ResponseEntity.ok(task);
//    }

    @PutMapping("/reminder")
    public ResponseEntity<Task> setReminder(
            @RequestBody TaskRequest request
    ) {
        Task task = taskService.setReminder(request);
        return ResponseEntity.ok(task);
    }

//    @DeleteMapping("/{taskId}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
//        taskService.deleteTask(taskId);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}

