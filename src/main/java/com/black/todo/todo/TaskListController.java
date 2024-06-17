package com.black.todo.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasklists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(@RequestParam String title, @RequestParam String email) { //Integer userId instead String
        return ResponseEntity.ok(taskListService.createTaskList(title, email));
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getTaskLists(@RequestParam Integer userId) {
        return ResponseEntity.ok(taskListService.getTaskLists(userId));
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Integer taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }
}
