package info.jonwarren.tasklogs.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task_totals_weekly")
public class TaskTotalWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @NotNull
    private Double total;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public TaskTotalWeek() {
    }

    public TaskTotalWeek(Long id) {
        setId(id);
    }

    public TaskTotalWeek(LocalDate endDate, Task task, Double total, User user) {
        setStartDate(endDate.minusDays(7));
        setEndDate(endDate);
        setTask(task);
        setTotal(total);
        setUser(user);
    }

    public TaskTotalWeek(LocalDate startDate, LocalDate endDate, Task task, Double total, User user) {
        setStartDate(startDate);
        setEndDate(endDate);
        setTask(task);
        setTotal(total);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //@formatter:off
        sb.append("id='").append(getId()).append("', ")
        .append("endDate='").append(getEndDate()).append("', ")
        .append("task=[").append(getTask()).append("], ")
        .append("total='").append(getTotal()).append("', ")
        .append("user=[").append(getUser()).append("]");
        //@formatter:on

        return sb.toString();
    }

}
