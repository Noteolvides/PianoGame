package Model;

import com.google.gson.*;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="Song")
public class Song implements Serializable {
    @Expose
    @Column(name="SongId")
    @Id
    private int SongID;
    @Expose
    @Column(name="Name")
    private String title;
    @Expose
    @Column(name="Duration")
    private int duration;
    @Expose
    @Column(name="Description")
    private String description;
    @Expose
    @ManyToOne (fetch = EAGER)
    @JoinColumn(name="Author")
    private User author;
    @Expose
    @Column(name="Plays")
    private int plays;
    @Expose
    @Column(name="File_Path")
    private String filePath;
    @Expose
    @Column(name="Privacity")
    private Boolean privacity;
    @Expose
    @ManyToOne (fetch=EAGER)
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

    public Song(String name, int duration, String description,int plays, String filePath,boolean privacity, User autor) {
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

    public Boolean getPrivacity() {
        return privacity;
    }

    public void setPrivacity(Boolean privacity) {
        this.privacity = privacity;
    }
}