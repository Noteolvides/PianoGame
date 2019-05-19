package Model;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that saves the information of the user.
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
@Table(name="User")
public class User implements Serializable {
    @Expose
    @Id
    @Column(name="Name")
    private String nameUser;
    @Expose
    @Column(name="Email")
    private String email;
    @Expose
    @Column (name = "UserCode")
    private String userCode;
    @Expose
    @Column(name="Password")
    private String password;
    @OneToMany(mappedBy="author", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Song> songs;
    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="Friendship", joinColumns={@JoinColumn(name="Name1")}, inverseJoinColumns={@JoinColumn(name="Name2")})
    private List <User> following;

    //@ManyToMany(mappedBy="following")
    //private List<User> followedBy = new ArrayList<User>();

    /**
     * Constructor that creates a empty user.
     */
    public User () {
        nameUser = "default";
        password = "default";
        email = "default";
        following = new ArrayList<User>();
        songs = new ArrayList<Song>();
    }

    /**
     * Constructor that creates a user with the all parameters received.
     * @param name Name of the user.
     * @param password Password of the user.
     * @param userCode Alphanumeric Code of the user.
     * @param email Email of the user.
     */
    public User(String name,String password, String userCode, String email) {
        this.nameUser = name;
        this.password = password;
        this.email = email;
        this.userCode = userCode;
    }

    /**
     * Constructor that creates a user with some parameters received.
     * @param name Name of the user.
     * @param password Password of the user.
     * @param email Email of the user.
     */
    public User(String name,String password, String email) {
        this.nameUser = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor that creates a user with a few parameters received.
     * @param name Name of the user.
     * @param password Password of the user.
     */
    public User(String name, String password) {
        this.nameUser = name;
        this.password = password;
    }


    /**
     * Constructor that creates a user with a parameter received.
     * @param name Name if tge user.
     */
    public User(String name) {
        this.nameUser = name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String name) {
        this.nameUser = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }


    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void addNewFriend (User user) {
        following.add(user);
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
