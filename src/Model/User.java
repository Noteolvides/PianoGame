package Model;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User implements Serializable {
    @Expose
    @Id
    @Column(name="Name")
    private String nameUser;
    @Expose
    @Column (name = "UserCode")
    private String userCode;
    @Expose
    @Column(name="Password")
    private String password;
    @Expose
    @Column(name="Email")
    private String email;
    @OneToMany(mappedBy="author", cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Song> songs;
    @Expose
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="Friendship", joinColumns={@JoinColumn(name="Name1")}, inverseJoinColumns={@JoinColumn(name="Name2")})
    private List <User> following;

    //@ManyToMany(mappedBy="following")
    //private List<User> followedBy = new ArrayList<User>();

    public User () {
        nameUser = "default";
        password = "default";
        email = "default";
        following = new ArrayList<User>();
        songs = new ArrayList<Song>();
    }


    public User(String name,String password, String userCode, String email) {
        this.nameUser = name;
        this.password = password;
        this.email = email;
        this.userCode = userCode;
    }

    public User(String name,String password, String email) {
        this.nameUser = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password) {
        this.nameUser = name;
        this.password = password;
    }



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
