package info.jonwarren.tasklogs.controller;

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

        sb.append("list of tasks go here...");

        return sb.toString();
    }

    @RequestMapping("/{name}")
    @ResponseBody
    public Task getByName(@PathVariable String name) {
        Task task = null;
        try {
            task = taskRepository.findByName(name);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return task;
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
        }

        return sb.toString();
    }

    //TODO: finish implementing this, but change name to id or add a way to find current tasks
//    @RequestMapping("/{name}/stop")
//    @ResponseBody
//    public String stopTask(@PathVariable String name) {
//        StringBuilder sb = new StringBuilder();
//        Task task = null;
//        Entry entry = null;
//        
//        try {
//            task = taskRepository.findByName(name);
//            if (task != null) {
//            }
//        }
//        
//        return sb.toString();
//    }
    
}
