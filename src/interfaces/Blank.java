package interfaces;

public class Blank {
    private int id;
    private int quantity;
    private String blankType;

    public Blank(int id, int quantity, String blankType) {
        this.id = id;
        this.quantity = quantity;
        this.blankType = blankType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBlankType() {
        return blankType;
    }

    public void setBlankType(String blankType) {
        this.blankType = blankType;
    }
}
