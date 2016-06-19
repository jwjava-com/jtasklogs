package info.jonwarren.tasklogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = getCurrentUser();

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
        User user = getCurrentUser();

        try {
            task = taskRepository.findByName(name);
            if (!task.getUser().equals(user)) {
                task = null; // prevent finding another user's task
            }
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
        User user = getCurrentUser();
        boolean isNewTask = false;
        Task task = null;

        try {
            task = taskRepository.findByName(name);
            if (!task.getUser().equals(user)) {
                task = null; // prevent finding another user's task
            }

            if (task == null) {
                isNewTask = true;
                task = new Task(name);
                task.setUser(user);
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
        User user = getCurrentUser();
        Task task = null;
        boolean isNewTask = false;
        Entry entry = null;

        try {
            task = taskRepository.findByName(name);
            if (!task.getUser().equals(user)) {
                task = null; // prevent starting another user's task
            }

            if (task == null) {
                isNewTask = true;
                task = new Task(name);
                task.setUser(user);
                taskRepository.save(task);
            }

            entry = new Entry(task);
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

    //TODO: decide if this belongs here or only in EntryController
    @RequestMapping("/{id}/stop")
    @ResponseBody
    public String stopTask(@PathVariable Long id) {
        StringBuilder sb = new StringBuilder();
        User user = getCurrentUser();
        Entry entry = null;

        try {
            entry = entryRepository.findOne(id);
            if (!entry.getUser().equals(user)) {
                entry = null; // prevent stopping another user's entry
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (entry != null) {
            //@formatter:off
            sb.append("Stopped entry with id '").append(id).append("'")
                .append(" for task named '").append(entry.getTask().getName()).append("'")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        } else {
            //@formatter:off
            sb.append("Could not stop entry with id '").append(id).append("'")
                .append(" for user [").append(user).append("]");
            //@formatter:on
        }
        return sb.toString();
    }

    // TODO: move to a common class for reuse
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByName(username);
        if (user == null) {
            user = new User(username);
            userRepository.save(user);
        }

        return user;
    }

}
