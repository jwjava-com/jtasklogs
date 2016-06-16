package info.jonwarren.tasklogs.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import info.jonwarren.tasklogs.model.Timesheet;
import info.jonwarren.tasklogs.model.User;

@Transactional
public interface TimesheetRepository extends CrudRepository<Timesheet, Long> {

    public Timesheet findByEndDate(Date date);

    public List<Timesheet> findAllByUser(User user);

    public List<Timesheet> findAllByEndDate(Date endDate);

}
