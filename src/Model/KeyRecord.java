package Model;

public class KeyRecord {
    private String key;
    private long start;
    private long end;

    public KeyRecord(String str, long currentTimeMillis) {
        this.key = str;
        this.start = currentTimeMillis;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
