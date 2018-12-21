public class Matrix4d {

    private double[][] m;

    public Matrix4d()
    {
        m = new double[4][4];
    }

    public Matrix4d initIdentity() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(i == j) {
                    m[i][j] = 1;
                } else {
                    m[i][j] = 0;
                }
            }
        }
        return this;
    }

    public Matrix4d mul(Matrix4d r) {
        Matrix4d res = new Matrix4d();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4;j++) {
                r.set(i, j, (m[i][0] * r.get(0, j) +
                             m[i][1] * r.get(1, j) +
                             m[i][2] * r.get(2, j) +
                             m[i][3] * r.get(3, j)));
            }
        }

        return res;
    }

    public double[][] getM() {
        return m;
    }

    public void setM(double[][] m) {
        this.m = m;
    }

    public double get(int x, int y) {
        return m[x][y];
    }

    public void set(int x, int y, double value) {
        m[x][y] = value;
    }
}
