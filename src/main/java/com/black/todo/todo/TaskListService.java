package com.black.todo.todo;

import com.black.todo.user.User;
import com.black.todo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;


    public TaskList createTaskList(String title, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        TaskList taskList = TaskList.builder()
                .title(title)
                .createdDate(new Date())
                .user(user)
                .build();

        return taskListRepository.save(taskList);
    }


    public List<TaskList> getTaskLists(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return taskListRepository.findByUser(user);
    }

    public void deleteTaskList(Integer taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}

