package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Song")
public class Song implements Serializable {
    @Column(name="SongId")
    @Id
    private int SongID;
    @Column(name="Name")
    private String title;
    @Column(name="Duration")
    private int duration;
    @Column(name="Description")
    private String description;
    @ManyToOne
    @JoinColumn(name="Author")
    private User author;
    @Column(name="Plays")
    private int plays;
    @Column(name="File_Path")
    private String filePath;
    @ManyToOne
    @JoinColumn(name="SystemID")
    private Syst system;

    public Song () {
        title = "default";
        duration = 0;
        description = "default";
    }

    public Song(String name, int duration, String description,int plays, String filePath, Syst syst) {
        this.title = name;
        this.duration = duration;
        this.description = description;
        this.plays = plays;
        this.filePath = filePath;
        this.system = syst;
    }

    public Song(String name, int duration, String description,int plays, String filePath, User autor) {
        this.title = name;
        this.duration = duration;
        this.description = description;
        this.plays = plays;
        this.filePath = filePath;
        this.author = autor;
    }

    public int getSongID() {
        return SongID;
    }

    public void setSongID(int songID) {
        SongID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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

    public Syst getSystem() {
        return system;
    }

    public void setSystem(Syst syst) {
        this.system = syst;
    }
}