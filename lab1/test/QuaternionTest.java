import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class QuaternionTest {
    private Quaternion quaternion = new Quaternion(1, 2, 3, 4);
    private double delta = 0.00001;

    @Test
    public void getAngleTest() {
        double angle = Math.PI / 4;
        Quaternion q = quaternion.create(angle, Math.sqrt(14) / 14, -Math.sqrt(14) / 7, (3 * Math.sqrt(14)) / 14);
        Assert.assertEquals(angle, q.getAngle(), delta);
    }

    @Test
    public void getUnitVectorTest() {
        double[] vector = new double[]{Math.sqrt(14) / 14, -Math.sqrt(14) / 7, (3 * Math.sqrt(14)) / 14};
        Quaternion quaternion2 = quaternion.create(Math.PI / 6, vector[0], vector[1], vector[2]);
        Assert.assertArrayEquals(vector, quaternion2.getUnitVector(), delta);
    }

    @Test
    public void getScalarTest() {
        Quaternion quaternion2 = quaternion.getScalar(2);
        Assert.assertThat(quaternion2, CoreMatchers.is(new Quaternion(2, 4, 6, 8)));
    }


    @Test
    public void getSummationTest() {
        Quaternion quaternion2 = quaternion.getSummation(new Quaternion(1, -3, -4, 0));
        Assert.assertThat(quaternion2, CoreMatchers.is(new Quaternion(2, -1, -1, 4)));
    }

    @Test
    public void getSubtractionTest() {
        Quaternion quaternion2 = quaternion.getSubtraction(new Quaternion(-1, -2, -3, -4));
        Assert.assertThat(quaternion2, CoreMatchers.is(new Quaternion(2, 4, 6, 8)));
    }

    @Test
    public void getConjugationTest() {
        Quaternion quaternion2 = quaternion.getConjugation();
        Assert.assertThat(quaternion2, CoreMatchers.is(new Quaternion(-1,-2,-3,4)));
    }

    @Test
    public void getMultiplierTest() {
        Quaternion quaternion2 = quaternion.getMultiplier(new Quaternion(10, 20, 30, 40));
        Assert.assertThat(quaternion2, CoreMatchers.is(new Quaternion(80, 160, 240, 20)));
    }

    @Test
    public void getNormTest() {
        Quaternion quaternion2 = quaternion.getNorm();
        Assert.assertTrue(quaternion2.equals(new Quaternion(0.18257418583505536, 0.3651483716701107, 0.5477225575051661, 0.7302967433402214), delta));
        Assert.assertFalse(quaternion2.equals(new Quaternion(1, 2, 3, 4), delta));
    }

    @Test
    public void getMagnitudeTest() {
        double magnitude = quaternion.getMagnitude();
        double expected = 5.477225575051661;
        Assert.assertTrue(Math.abs(magnitude - expected) < delta);
    }

    @Test
    public void getInverseTest() {
        Quaternion quaternion2 = quaternion.getInverse();
        Assert.assertTrue(quaternion2.equals(new Quaternion(-0.03333333333333333, -0.06666666666666667,-0.1, 0.13333333333333333), delta));
    }

    @Test
    public void getVectorTest() {
        double[] vector = quaternion.getVector();
        Assert.assertArrayEquals(new double[]{1.0, 2.0, 3.0}, vector, delta);
    }

    @Test
    public void getScalarPartTest() {
        double scalarPart = quaternion.getScalarPart();
        Assert.assertThat(scalarPart, CoreMatchers.is(4.0));

    }
}