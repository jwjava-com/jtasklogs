package info.jonwarren.tasklogs.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.TaskTotalWeek;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface TaskTotalWeekRepository extends CrudRepository<TaskTotalWeek, Long> {

    public List<TaskTotalWeek> findAllByUser(User user);

    public List<TaskTotalWeek> findByEndDateAndUser(LocalDate date, User user);

    public List<TaskTotalWeek> findAllByTaskAndUser(Task task, User user);

    public List<TaskTotalWeek> findAllByEndDateAndUser(LocalDate endDate, User user);

}
