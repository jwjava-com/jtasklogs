package info.jonwarren.tasklogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.repository.EntryRepository;
import info.jonwarren.tasklogs.repository.UserRepository;
import info.jonwarren.tasklogs.service.UserService;

@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    @ResponseBody
    public String getAllEntries(Model model) {
        StringBuilder sb = new StringBuilder();
        User user = UserService.getCurrentUser(userRepository);

        List<Entry> entries = null;
        entries = (List<Entry>) entryRepository.findAllByUser(user);
        if (entries != null && !entries.isEmpty()) {
            sb.append("Found these entries for user [").append(user).append("]:");
            entries.forEach(entry -> sb.append(entry));
        } else {
            sb.append("No entries were found for user [").append(user).append("]");
        }

        return sb.toString();
    }

    @RequestMapping("/{id}/stop")
    @ResponseBody
    public String stopTask(@PathVariable Long id) {
        StringBuilder sb = new StringBuilder();
        User user = UserService.getCurrentUser(userRepository);
        Entry entry = null;

        try {
            entry = entryRepository.findOne(id);
//            if (!entry.getUser().equals(user)) {
//                entry = null; // prevent stopping another user's entry
//            }
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

}
