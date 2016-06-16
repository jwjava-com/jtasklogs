package info.jonwarren.tasklogs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Entry;
import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.model.UserDay;

public interface UserDayRepository extends CrudRepository<UserDay, Long> {

    public List<UserDay> findAllByUser(User user);

    public List<Entry> findAllByStartTime(Date startTime);

    public List<Entry> findAllByStopTime(Date stopTime);

}
