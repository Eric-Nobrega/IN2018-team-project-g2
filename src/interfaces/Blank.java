package interfaces;

public class Blank {
    private long id;
    private String blankType;

    public Blank(long id,  String blankType) {
        this.id = id;
        this.blankType = blankType;
    }

    public Blank() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlankType() {
        return blankType;
    }

    public void setBlankType(String blankType) {
        this.blankType = blankType;
    }
}
