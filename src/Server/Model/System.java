package Server.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="System")
public class System {
    @Column(name="name")
    @Id
    private String name;
    @Column(name="date")
    private Date date;
    @Column(name="totalofusers")
    private int totalUsers;

    public System(String name, Date date, int totalUsers) {
        this.name = name;
        this.date = date;
        this.totalUsers = totalUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
}
