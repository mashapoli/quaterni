import java.util.Objects;

import static java.lang.Math.*;


public class Quaternion {
    private double x, y, z, w;

    public Quaternion(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Quaternion create(final double angle, final double x, final double y, final double z) {
        double newX, newY, newZ, newW;

        double halfAngle = angle / 2;
        newW = cos(halfAngle);
        if (x == 0 || y == 0 || z == 0) {
            newX = newY = newZ = 0;

        } else {
            double newSin = sin(halfAngle);
            double unitVector = sqrt(x * x + y * y + z * z);
            if (abs(unitVector - 1.0) > 1e-6) {
                throw new IllegalStateException();
            }
            newX = x * newSin * unitVector;
            newY = y * newSin * unitVector;
            newZ = z * newSin * unitVector;
        }
        return new Quaternion(newX, newY, newZ, newW);
    }

    public double getAngle() {
        return acos(this.w) * 2;
    }

    public double[] getUnitVector() {
        double angle = getAngle() / 2;
        double newSinAngle  = sin(angle);
        return new double[]{this.x / newSinAngle, this.y / newSinAngle, this.z / newSinAngle};
    }

    public Quaternion getScalar(double s) {
        return new Quaternion(s * x, s * y, s * z, s * w);
    }

    public Quaternion add(Quaternion other) {
        return new Quaternion(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public Quaternion subtract(Quaternion other) {
        return new Quaternion(x - other.x, y - other.y, z - other.z, w - other.w);
    }

    public Quaternion getConjugation() {
        return new Quaternion(-x,-y,-z,w);
    }

    public Quaternion multiply(Quaternion other) {
        double multiX = w * other.x + x * other.w + y * other.z - z * other.y;
        double multiY = w * other.y + y * other.w + z * other.x - x * other.z;
        double multiZ = w * other.z + z * other.w + x * other.y - y * other.x;
        double multiW = w * other.w - x * other.x - y * other.y - z * other.z;
        return new Quaternion(multiX, multiY, multiZ, multiW);
    }

    public double getMagnitude() {
        return sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion getNorm() {
        double magnitude = getMagnitude();
        if (abs(magnitude) < 1e-5) {
            throw new IllegalStateException();
        }
        double normX = x / magnitude;
        double normY = y / magnitude;
        double normZ = z / magnitude;
        double normW = w / magnitude;
        return new Quaternion(normX, normY, normZ, normW);
    }

    public Quaternion getInverse() {
        double powGetMagnitude = pow(getMagnitude(), 2.0);
        return new Quaternion(-x/powGetMagnitude,-y/powGetMagnitude,-z/powGetMagnitude,w/powGetMagnitude);
    }

    public double[] getVector() {
        return new double[]{x, y, z};
    }

    public double getScalarPart() {
        return w;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quaternion that = (Quaternion) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0 &&
                Double.compare(that.w, w) == 0;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    boolean equals(Quaternion other, double delta) {
        return abs(x - other.x) < delta &&
                abs(y - other.y) < delta &&
                abs(z - other.z) < delta &&
                abs(w - other.w) < delta
                ;

    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "xi=" + x +
                ", yj=" + y +
                ", zk=" + z +
                ", w=" + w +
                '}';
    }

}
