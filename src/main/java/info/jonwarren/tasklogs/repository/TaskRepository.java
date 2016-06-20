package info.jonwarren.tasklogs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Task;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface TaskRepository extends CrudRepository<Task, Long> {

    public List<Task> findAllByUser(User user);

    public Task findByNameAndUser(String name, User user);

    public List<Task> findAllByIsBillableAndUser(Boolean isBillable, User user);

}
