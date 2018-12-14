import java.util.Vector;

public class Vector2d {
    double x;
    double y;
    Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double lenght() {
        return Math.sqrt(x*x + y*y);
    }

    public double dot(Vector2d r) {
        return x * r.getX() + y * r.getY();
    }

    public Vector2d normalize() {
        double lenght = lenght();

        x /= lenght;
        y /= lenght;
        return this;
    }

    public Vector2d rotate(double angle) {
        return  null;
    }

    public Vector2d add(Vector2d r) {
        return new Vector2d(x + r.getX(), y + r.getY());
    }

    public Vector2d add(double r) {
        return  new Vector2d(x + r, y + r);
    }

    public Vector2d subtract(Vector2d r) {
        return new Vector2d(x - r.getX(), y - r.getY());
    }

    public Vector2d subtract(double r) {
        return  new Vector2d(x - r, y - r);
    }

    public Vector2d mul(Vector2d r) {
        return new Vector2d(x * r.getX(), y * r.getY());
    }

    public Vector2d mul(double r) {
        return  new Vector2d(x * r, y * r);
    }

    public Vector2d div(Vector2d r) {
        return new Vector2d(x / r.getX(), y / r.getY());
    }

    public Vector2d div(double r) {
        return  new Vector2d(x / r, y / r);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
