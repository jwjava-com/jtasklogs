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

    public List<Entry> findAllByUser(User user);

    public List<Entry> findAllByTaskAndUser(Task task, User user);

    public List<Entry> findAllByStartTimeAndUser(Instant startTime, User user);

    public List<Entry> findAllByStopTimeAndUser(Instant stopTime, User user);

}
