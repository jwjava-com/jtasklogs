package info.jonwarren.tasklogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.repository.EntryRepository;
import info.jonwarren.tasklogs.repository.TaskRepository;
import info.jonwarren.tasklogs.repository.UserRepository;
import info.jonwarren.tasklogs.service.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    @ResponseBody
    public String getAllTasks(Model model) {
        StringBuilder sb = new StringBuilder();
        User user = UserService.getCurrentUser(userRepository);

        List<Task> tasks = null;
        tasks = (List<Task>) taskRepository.findAllByUser(user);
        if (tasks != null && !tasks.isEmpty()) {
            sb.append("Found these tasks for user [").append(user).append("]:");
            tasks.forEach(task -> sb.append(task));
        } else {
            sb.append("No tasks were found for user [").append(user).append("]");
        }

        return sb.toString();
    }

    @RequestMapping("/{name}")
    @ResponseBody
    public String getByName(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        Task task = null;
        User user = UserService.getCurrentUser(userRepository);

        try {
            task = taskRepository.findByNameAndUser(name, user);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (task != null) {
            sb.append("Found task [").append(task).append("]");
        } else {
            //@formatter:off
            sb.append("Could not find task with name '").append(name).append("'")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        }

        return sb.toString();
    }

    @RequestMapping("/{name}/create")
    @ResponseBody
    public String createTask(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        User user = UserService.getCurrentUser(userRepository);
        boolean isNewTask = false;
        Task task = null;

        try {
            task = taskRepository.findByNameAndUser(name, user);

            if (task == null) {
                isNewTask = true;
                task = new Task(name, user);
                taskRepository.save(task);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (task != null) {
            if (isNewTask) {
                //@formatter:off
                sb.append("Created task [").append(task).append("]")
                    .append(" for user [").append(user).append("]");
                //@formatter:on
            } else {
                //@formatter:off
                sb.append("Task named '").append(name).append("'")
                    .append(" already existed for user [").append(user).append("]");
                //@formatter:on
            }
        } else {
            //@formatter:off
            sb.append("Could not create task with name '").append(name).append("'")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        }

        return sb.toString();
    }

    @RequestMapping("/{name}/start")
    @ResponseBody
    public String startTask(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        User user = UserService.getCurrentUser(userRepository);
        Task task = null;
        boolean isNewTask = false;
        Entry entry = null;

        try {
            task = taskRepository.findByNameAndUser(name, user);

            if (task == null) {
                isNewTask = true;
                task = new Task(name, user);
                taskRepository.save(task);
            }

            entry = new Entry(task, user);
            entryRepository.save(entry);

        } catch (Exception e) {
            //TODO: handle exception
        }

        if (task != null) {
            //@formatter:off
            sb.append("Started ").append((isNewTask)?"a new":"an existing")
                .append(" task [").append(task).append("]")
                .append(" with entry [").append(entry).append("]")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        } else {
            //@formatter:off
            sb.append("Could not start task with name '").append(name).append("'")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        }

        return sb.toString();
    }

}
