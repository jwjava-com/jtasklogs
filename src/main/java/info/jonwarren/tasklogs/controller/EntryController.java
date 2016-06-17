package info.jonwarren.tasklogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.repository.EntryRepository;

@RestController
@RequestMapping("/entries")
public class EntryController {
    
    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping("/{id}/stop")
    @ResponseBody
    public String stopEntry(@PathVariable Long id) {
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
