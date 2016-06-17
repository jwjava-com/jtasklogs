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
@Table(name = "user_day")
public class UserDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "start_ts")
    private Instant startTime;

    @Column(name = "stop_ts")
    private Instant stopTime;

    @NotNull
    @Column(name = "billable_total")
    private Double billableTotal;

    @NotNull
    @Column(name = "nonbillable_total")
    private Double nonbillableTotal;

    public UserDay() {
    }

    public UserDay(User user, Instant startTime, Instant stopTime, Double billableTotal, Double nonbillableTotal) {
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Double getBillableTotal() {
        return billableTotal;
    }

    public void setBillableTotal(Double billableTotal) {
        this.billableTotal = billableTotal;
    }

    public Double getNonbillableTotal() {
        return nonbillableTotal;
    }

    public void setNonbillableTotal(Double nonbillableTotal) {
        this.nonbillableTotal = nonbillableTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //@formatter:off
        sb.append("id='").append(getId()).append("', ")
        .append("user=[").append(getUser()).append("], ")
        .append("startTime='").append(getStartTime()).append("', ")
        .append("stopTime='").append(getStopTime()).append("', ")
        .append("billableTotal='").append(getBillableTotal()).append("', ")
        .append("nonbillableTotal='").append(getNonbillableTotal()).append("'");
        //@formatter:on

        return sb.toString();
    }

}
