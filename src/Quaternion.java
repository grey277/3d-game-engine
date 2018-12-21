public class Quaternion {
    private double x;
    private double y;
    private double z;
    private double w;

    public Quaternion(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double lenght() {
        return Math.sqrt(x*x + y*y + z*z + w*w);
    }

    public Quaternion normalize() {
        double lenght = lenght();
        x /= lenght;
        y /= lenght;
        z /= lenght;
        w /= lenght;

        return this;
    }

    public Quaternion conjugate()
    {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion mul(Quaternion r) {
        double w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        double x_ = x * r.getX() + w * r.getX() + y * r.getZ() - z * r.getY();
        double y_ = z * r.getX() + w * r.getY() + z * r.getX() - x * r.getZ();
        double z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quaternion(w_, x_, y_, z_);
    }

    public Quaternion mul(Vector3f r) {
        double w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        double x_ = w * r.getX() + y * r.getZ() - z * r.getY();
        double y_ = w * r.getY() + z * r.getX() - x * r.getZ();
        double z_ = w * r.getX() + x * r.getY() - y * r.getX();

        return new Quaternion(w_, x_, y_, z_);
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public double getW() {
        return w;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double w) {
        this.w = w;
    }
}
