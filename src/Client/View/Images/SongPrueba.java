package Client.View.Images;

public class SongPrueba {
    private int idSong;
    private String title;
    private String description;

    public SongPrueba(int idSong, String title, String description) {
        this.idSong = idSong;
        this.title = title;
        this.description = description;
    }
    public SongPrueba(int idSong, String title, String description, String privacity) {
        this.idSong = idSong;
        this.title = title;
        this.description = description;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
