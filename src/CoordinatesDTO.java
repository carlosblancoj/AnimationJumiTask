public class CoordinatesDTO {

    private Integer x;
    private Integer y;

    // * Constructor

    public CoordinatesDTO(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    // * Getters & Setters

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
