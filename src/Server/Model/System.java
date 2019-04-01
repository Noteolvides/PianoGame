package Server.Model;

import java.util.Date;

public class System {
    private String name;
    private Date date;
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
