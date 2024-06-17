package com.black.todo.todo;

import com.black.todo.user.User;
import com.black.todo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;

    public TaskList createTaskList(String title, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TaskList taskList = user.createTaskList(title); //TaskList taskList = user.createTaskList(title);
        return taskListRepository.save(taskList);
    }

    public List<TaskList> getTaskLists(Integer userId) {
        return taskListRepository.findByUserId(userId);
    }

    public void deleteTaskList(Integer taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}

