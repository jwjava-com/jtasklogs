package info.jonwarren.tasklogs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "timesheets")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @Column(name = "billable_total")
    private Double billableTotal;

    @NotNull
    @Column(name = "nonbillable_total")
    private Double nonbillableTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Timesheet() {
    }

    public Timesheet(Long id) {
        setId(id);
    }

    public Timesheet(Date endDate, Double billableTotal, Double nonbillableTotal) {
        setEndDate(endDate);
        setBillableTotal(nonbillableTotal);
        setNonbillableTotal(nonbillableTotal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        .append("startDate='").append(getStartDate()).append("', ")
        .append("endDate='").append(getEndDate()).append("', ")
        .append("billableTotal='").append(getBillableTotal()).append("', ")
        .append("nonbillableTotal='").append(getNonbillableTotal()).append("', ")
        .append("user=[").append(getUser()).append("]");
        //@formatter:on

        return sb.toString();
    }

}
