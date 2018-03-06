import java.util.Objects;

public class Quaternion {
    private double  x, y, z, w;

    public Quaternion(double x,double y,double z,double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    Quaternion getScalar(double s){
        return new Quaternion(s*x , s*y, s*z, s*w);
    }
    Quaternion getSummation(Quaternion other){
        return new Quaternion(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    Quaternion getSubtraction(Quaternion other){
        return new Quaternion(x - other.x, y - other.y, z - other.z, w - other.w);
    }
    double getConjugation(){
        return w - x - y -z;
    }
    Quaternion getMultiplier(Quaternion other) {
        double multiX = w * other.x + x * other.w + y * other.z - z * other.y;
        double multiY = w * other.y + y * other.w + z * other.x - x * other.z;
        double multiZ = w * other.z + z * other.w + x * other.y - y * other.x;
        double multiW = w * other.w - x * other.x - y * other.y - z * other.z;
        return new Quaternion(multiX, multiY, multiZ, multiW);
    }

    double getMagnitude(){
        return Math.sqrt(x*x + y*y + z*z + w*w);
    }
    Quaternion getNorm(){
        double magnitude = getMagnitude();
        if (Math.abs(magnitude) < 1e-5) {
            throw new IllegalStateException() ;
        }
        double normX = x/ magnitude;
        double normY = y/ magnitude;
        double normZ = z/ magnitude;
        double normW = w/ magnitude;
        return new Quaternion(normX,normY,normZ,normW);
    }
    double getInverse(){
        return getConjugation()/Math.pow(getMagnitude(),2.0);
    }
    double[] getVector() {
        return new double[]{x, y, z};
    }

    double getScalarPart(){
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
        return Math.abs(x - other.x) < delta &&
                Math.abs(y - other.y) < delta &&
                Math.abs(z - other.z) < delta &&
                Math.abs(w - other.w) < delta
                ;

    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }

}
