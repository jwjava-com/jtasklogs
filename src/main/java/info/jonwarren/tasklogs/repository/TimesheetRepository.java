package info.jonwarren.tasklogs.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Timesheet;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface TimesheetRepository extends CrudRepository<Timesheet, Long> {

    public List<Timesheet> findAllByUser(User user);

    public List<Timesheet> findByEndDateAndUser(LocalDate date, User user);

    public List<Timesheet> findAllByEndDateAndUser(LocalDate endDate, User user);

}
