package info.jonwarren.tasklogs.model;

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
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "is_billable")
    private Boolean isBillable;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(Long id) {
        setId(id);
    }

    public Task(String name, User user) {
        setName(name);
        setIsBillable(false);
        setUser(user);
    }

    public Task(String name, Boolean isBillable, User user) {
        setName(name);
        setIsBillable(isBillable);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(Boolean isBillable) {
        this.isBillable = isBillable;
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
        .append("name='").append(getName()).append("', ")
        .append("isBillable='").append(getIsBillable()).append("', ")
        .append("user=[").append(getUser()).append("]");
        //@formatter:on

        return sb.toString();
    }

}
