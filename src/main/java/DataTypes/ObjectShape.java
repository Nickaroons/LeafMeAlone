package DataTypes;

public enum ObjectShape {
    CIRCLE("CIRCLE"), RECTANGLE("RECT"), LINE("LINE");

    public String shape;

    private ObjectShape(final String shape) {
        this.shape = shape;
    }
}
