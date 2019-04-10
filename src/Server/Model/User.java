package Server.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Column(name="name")
    @Id
    private String name;
    @Column(name="url")
    private String url;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @OneToMany(mappedBy="name", cascade= CascadeType.ALL)
    private List<Song> songs;

    public User(String name, String url, String password, String email) {
        this.name = name;
        this.url = url;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
