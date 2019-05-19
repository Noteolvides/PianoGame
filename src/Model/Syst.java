package Model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Class that saves the information of the System.
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
@Entity
@Table(name="Syst")
public class Syst implements Serializable {
    @Expose
    @Id
    @Column(name = "ID")
    private int ID;
    @Expose
    @Column(name="Name")
    private String nameSyst;
    @Expose
    @Column(name="Date")
    private Date date;
    @Expose
    @Column(name="TotalOfUsers")
    private int totalUsers;
    @OneToMany(mappedBy="system", cascade= CascadeType.ALL, fetch=EAGER)
    private List<Song> songs;

    /**
     * Constructor that creates an empty System.
     */
    public Syst() {
        nameSyst = "default";
        songs = new ArrayList<Song>();
    }

    /**
     * Constructor that creates a System with the parameters received.
     * @param name System name.
     * @param date Date of the system.
     * @param totalUsers Total users in the system.
     */
    public Syst(String name, Date date, int totalUsers) {
        this.nameSyst = name;
        this.date = date;
        this.totalUsers = totalUsers;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return nameSyst;
    }

    public void setName(String name) {
        this.nameSyst = name;
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
