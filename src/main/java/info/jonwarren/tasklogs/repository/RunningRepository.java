package info.jonwarren.tasklogs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Running;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface RunningRepository extends CrudRepository<Running, Long> {

    public List<Running> findAllByUser(User user);

}
