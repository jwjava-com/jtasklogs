package info.jonwarren.tasklogs.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface EntryRepository extends CrudRepository<Entry, Long> {

    public List<Entry> findAllByTask(Task task);
    
    public List<Entry> findAllByStartTime(Date startTime);

    public List<Entry> findAllByStopTime(Date stopTime);
    
    public List<Entry> findAllByUser(User user);

}
