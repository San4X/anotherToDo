package com.black.todo.todo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    private int priority;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime reminder;

    @ManyToOne
    @JoinColumn(name = "task_list_id", nullable = false)
    @JsonManagedReference
    private TaskList taskList;

//    public void markAsComplete() {
//        this.status = "COMPLETED";
//    }

//    public void setReminder(LocalDateTime reminder) {
//        this.reminder = reminder;
//    }
}
