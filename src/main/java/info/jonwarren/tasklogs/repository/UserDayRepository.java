package info.jonwarren.tasklogs.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.model.UserDay;

public interface UserDayRepository extends CrudRepository<UserDay, Long> {

    public List<UserDay> findAllByUser(User user);

    public List<Entry> findAllByStopTimeAndUser(Instant stopTime, User user);

    public List<Entry> findAllByStartTimeAndUser(Instant startTime, User user);

}
