package Server.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Song")
public class Song implements Serializable {
    @Column(name="SongId")
    @Id
    private int SongID;
    @Column(name="name")
    private String name;
    @Column(name="duration")
    private int duration;
    @Column(name="description")
    private String description;
    @ManyToOne
    @JoinColumn(name="author")
    private User author;
    @Column(name="plays")
    private int plays;
    @Column(name="filepath")
    private String filePath;


    public Song(int songID, String name, int duration, String description, User author, int plays, String filePath) {
        SongID = songID;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.author = author;
        this.plays = plays;
        this.filePath = filePath;
    }

    public int getSongID() {
        return SongID;
    }

    public void setSongID(int songID) {
        SongID = songID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
