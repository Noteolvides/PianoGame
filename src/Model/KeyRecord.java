package Model;

public class KeyRecord {
    private int id;
    private String key;
    private long start;
    private long end;

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
