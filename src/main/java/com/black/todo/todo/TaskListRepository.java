package com.black.todo.todo;

import com.black.todo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
    List<TaskList> findByUser(User user); //findByUserId(Integer userId);
}
