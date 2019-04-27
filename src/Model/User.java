package Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name="Name")
    private String nameUser;
    @Column(name="Photo_Path")
    private String url;
    @Column(name="Password")
    private String password;
    @Column(name="Email")
    private String email;
    @OneToMany(mappedBy="author", cascade= CascadeType.ALL)
    private List<Song> songs;
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
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


    public User(String name, String url, String password, String email) {
        this.nameUser = name;
        this.url = url;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password) {
        this.nameUser = name;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.nameUser = username;
        this.email = email;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
