package com.black.todo.todo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private Date dueDate;
    private int priority;
    private String status;
    private Date reminder;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    public void markAsComplete() {
        this.status = "COMPLETED";
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }
}
