package info.jonwarren.tasklogs.repository;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface EntryRepository extends CrudRepository<Entry, Long> {

    public List<Entry> findAllByTask(Task task);

    public List<Entry> findAllByStartTime(Instant startTime);

    public List<Entry> findAllByStopTime(Instant stopTime);

    public List<Entry> findAllByUser(User user);

}
