package com.black.todo.todo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasklists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;


    @Operation(
            description = "Create new task list"
    )
    @PostMapping
    public ResponseEntity<TaskList> createTaskList(
            @RequestBody TaskListRequest request) {
        TaskList taskList = taskListService.createTaskList(request.getTitle(), request.getUsername());
        return ResponseEntity.ok(taskList);
    }


    @Operation(
            description = "Get all task lists"
    )
    @GetMapping
    public ResponseEntity<List<TaskList>> getTaskLists(@RequestParam String email) {
        List<TaskList> taskLists = taskListService.getTaskLists(email);
        return ResponseEntity.ok(taskLists);
    }

    @Operation(
            description = "Delete certain task list"
    )
    @DeleteMapping("/delete/{taskListId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Integer taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }
}
