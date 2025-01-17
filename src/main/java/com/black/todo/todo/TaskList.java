package com.black.todo.todo;

import com.black.todo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Task> tasks = new ArrayList<>();

    public Task addTask(String description, LocalDate dueDate, int priority) {
        Task task = new Task();
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);
        task.setTaskList(this);
        tasks.add(task);
        return task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> sortTasksBy(String attribute) {
        // Логіка сортування завдань за певним атрибутом
        return tasks.stream()
                .sorted((t1, t2) -> {
                    switch (attribute) {
                        case "description":
                            return t1.getDescription().compareTo(t2.getDescription());
                        case "dueDate":
                            return t1.getDueDate().compareTo(t2.getDueDate());
                        case "priority":
                            return Integer.compare(t1.getPriority(), t2.getPriority());
                        case "status":
                            return t1.getStatus().compareTo(t2.getStatus());
                        case "reminder":
                            return t1.getReminder().compareTo(t2.getReminder());
                        default:
                            return 0;
                    }
                })
                .collect(Collectors.toList());
    }
}

