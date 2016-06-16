package info.jonwarren.tasklogs.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.model.TaskTotalWeek;

@Transactional
public interface TaskTotalWeekRepository extends CrudRepository<TaskTotalWeek, Long> {

    public TaskTotalWeek findByEndDate(Date date);

    public List<TaskTotalWeek> findAllByTask(Task task);

    public List<TaskTotalWeek> findAllByUser(User user);

    public List<TaskTotalWeek> findAllByEndDate(Date endDate);

}
