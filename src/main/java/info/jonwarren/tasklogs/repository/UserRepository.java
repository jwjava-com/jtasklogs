package info.jonwarren.tasklogs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByName(String name);

}
