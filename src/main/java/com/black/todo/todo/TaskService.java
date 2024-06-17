package com.black.todo.todo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public Task addTask(String description, Date dueDate, int priority, Integer taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new EntityNotFoundException("TaskList not found"));
        Task task = taskList.addTask(description, dueDate, priority);
        return taskRepository.save(task);
    }

    public List<Task> getTasks(Integer taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    public void markTaskAsComplete(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.markAsComplete();
        taskRepository.save(task);
    }

    public void setTaskReminder(Integer taskId, Date reminder) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setReminder(reminder);
        taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
