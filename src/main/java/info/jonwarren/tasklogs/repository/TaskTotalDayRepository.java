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

    public TaskTotalDay findByDate(LocalDate date);

    public List<TaskTotalDay> findAllByTask(Task task);

    public List<TaskTotalDay> findAllByUser(User user);

    public List<TaskTotalDay> findAllByDate(LocalDate date);

}
