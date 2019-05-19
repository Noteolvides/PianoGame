package Model;

/**
 * This class contains the note information of a note that was presed and how long it was
 *
 * @version 1.0
 * @since 2019-05-16
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class KeyRecord {
    private int id;
    private String key;
    private long start;
    private long end;

    /**
     *
     * @param str The note
     * @param currentTimeMillis StartTime
     * @param id Identifier
     */
    public KeyRecord(String str, long currentTimeMillis,int id) {
        this.id = id;
        this.key = str;
        this.start = currentTimeMillis;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getKey() {
        return key;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
