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
import info.jonwarren.tasklogs.repository.EntryRepository;
import info.jonwarren.tasklogs.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping(value = "/")
    @ResponseBody
    public String getAllTasks(Model model) {
        StringBuilder sb = new StringBuilder();

        List<Task> tasks = (List<Task>) taskRepository.findAll();
        if (tasks != null && !tasks.isEmpty()) {
            sb.append("Found these tasks:");
            tasks.forEach(task -> sb.append(task));
        } else {
            sb.append("No tasks were found");
        }

        return sb.toString();
    }

    @RequestMapping("/{name}")
    @ResponseBody
    public String getByName(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        Task task = null;

        try {
            task = taskRepository.findByName(name);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (task != null) {
            sb.append("Found task [").append(task).append("]");
        } else {
            sb.append("Couldn't find task with name '").append(name).append("'");
        }

        return sb.toString();
    }

    @RequestMapping("/{name}/create")
    @ResponseBody
    public String createTask(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        Task task = null;

        try {
            task = new Task(name);
            taskRepository.save(task);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (task != null) {
            sb.append("Created task [").append(task).append("]");
        }

        return sb.toString();
    }

    @RequestMapping("/{name}/start")
    @ResponseBody
    public String startTask(@PathVariable String name) {
        StringBuilder sb = new StringBuilder();
        Task task = null;
        boolean isNewTask = false;
        Entry entry = null;

        try {
            task = taskRepository.findByName(name);
            if (task == null) {
                isNewTask = true;
                task = new Task(name);
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
            .append(" with entry [").append(entry).append("]");
            //@formatter:on
        } else {
            sb.append("Could not start task with name '").append(name).append("'");
        }

        return sb.toString();
    }

    //TODO: decide if this belongs here or only in EntryController
    @RequestMapping("/{id}/stop")
    @ResponseBody
    public String stopTask(@PathVariable Long id) {
        StringBuilder sb = new StringBuilder();
        Entry entry = null;

        try {
            entry = entryRepository.findOne(id);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (entry != null) {
            //@formatter:off
            sb.append("Stopped entry with id '").append(id).append("'")
            .append(" for task named '").append(entry.getTask().getName()).append("'");
            //@formatter:on
        } else {
            sb.append("");
        }
        return sb.toString();
    }

}
