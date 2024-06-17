package com.black.todo.todo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

//    public Task addTask(String description, Date dueDate, int priority, Integer taskListId) {
//        TaskList taskList = taskListRepository.findById(taskListId)
//                .orElseThrow(() -> new EntityNotFoundException("TaskList not found"));
//        Task task = taskList.addTask(description, dueDate, priority);
//        return taskRepository.save(task);
//    }

    public Task addTask(TaskRequest request) {
        TaskList taskList = taskListRepository.findById(request.getTaskListId())
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found"));

        Task task = Task.builder()
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .priority(request.getPriority())
                .status("Pending")
                .taskList(taskList)
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getTasks(Integer taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

//    public void markTaskAsComplete(Integer taskId) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
//        task.markAsComplete();
//        taskRepository.save(task);
//    }

    public Task markAsComplete(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setStatus("Completed");
        return taskRepository.save(task);
    }

//    public void setTaskReminder(Integer taskId, Date reminder) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
//        task.setReminder(reminder);
//        taskRepository.save(task);
//    }

//    public Task setReminder(Integer taskId, LocalDateTime reminder) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
//        task.setReminder(reminder);
//        return taskRepository.save(task);
//    }

    public Task setReminder(TaskRequest request) {
        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setReminder(request.getReminder());
        return taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
