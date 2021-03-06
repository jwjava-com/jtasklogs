package info.jonwarren.tasklogs.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.TaskTotalDay;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface TaskTotalDayRepository extends CrudRepository<TaskTotalDay, Long> {

    public List<TaskTotalDay> findAllByUser(User user);

    public List<TaskTotalDay> findByDateAndUser(LocalDate date, User user);

    public List<TaskTotalDay> findAllByTaskAndUser(Task task, User user);

    public List<TaskTotalDay> findAllByDateAndUser(LocalDate date, User user);

}
