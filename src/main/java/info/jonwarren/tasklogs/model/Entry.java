package info.jonwarren.tasklogs.model;

import java.time.Instant;

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
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @NotNull
    @Column(name = "start_ts")
    private Instant startTime;

    @Column(name = "stop_ts")
    private Instant stopTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Entry() {
    }

    public Entry(Long id) {
        setId(id);
    }

    public Entry(Task task, User user) {
        setTask(task);
        setStartTime(Instant.now());
        setUser(user);
    }

    public Entry(Task task, Instant startTime, User user) {
        setTask(task);
        setStartTime(startTime);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getStopTime() {
        return stopTime;
    }

    public void setStopTime(Instant stopTime) {
        this.stopTime = stopTime;
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
        .append("task=[").append(getTask()).append("], ")
        .append("startTime='").append(getStartTime()).append("', ")
        .append("stopTime='").append(getStopTime()).append("', ")
        .append("user=[").append(getUser()).append("]");
        //@formatter:on

        return sb.toString();
    }

}
