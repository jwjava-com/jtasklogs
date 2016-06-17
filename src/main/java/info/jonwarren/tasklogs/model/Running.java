package info.jonwarren.tasklogs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "running_entries")
public class Running {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_user")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
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
        .append("entry=[").append(getEntry()).append("], ")
        .append("user=[").append(getUser()).append("]");
        //@formatter:on

        return sb.toString();
    }

}
