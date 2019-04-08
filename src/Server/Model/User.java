package Server.Model;

import javax.persistence.*;

@Entity
@Table(name="User")
@OneToMany(mappedBy="name", cascade= CascadeType.ALL)
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
}
