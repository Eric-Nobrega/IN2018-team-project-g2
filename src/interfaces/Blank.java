package interfaces;

public class Blank {
    private long id;
    private String TYPE;

    public Blank(long id,  String blankType) {
        this.id = id;
        this.TYPE = blankType;
    }

    public Blank() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
