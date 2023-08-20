package primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class is used for some internal utilities, e.g. controlling accuracy
 *
 * @author Dan
 */
public abstract class Util {
    // It is binary, equivalent to ~1/1,000,000,000,000 in decimal (12 digits)
    private static final int ACCURACY = -40;

    /**
     * Empty private constructor to hide the public one
     */
    private Util() {
    }

    // double store format (bit level):
    //    seee eeee eeee (1.)mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
    // 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
    // the number is m+2^e where 1<=m<2
    // NB: exponent is stored "normalized" (i.e. always positive by adding 1023)
    private static int getExp(double num) {
        // 1. doubleToRawLongBits: "convert" the stored number to set of bits
        // 2. Shift all 52 bits to the right (removing mantissa)
        // 3. Zero the sign of number bit by mask 0x7FF
        // 4. "De-normalize" the exponent by subtracting 1023
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    /**
     * Checks whether the number is [almost] zero
     *
     * @param number the number to check
     * @return true if the number is zero or almost zero, false otherwise
     */
    public static boolean isZero(double number) {
        return getExp(number) < ACCURACY;
    }

    /**
     * Aligns the number to zero if it is almost zero
     *
     * @param number the number to align
     * @return 0.0 if the number is very close to zero, the number itself otherwise
     */
    public static double alignZero(double number) {
        return getExp(number) < ACCURACY ? 0.0 : number;
    }

    /**
     * Aligns the number to the closest hole number if the deference is almost zero
     *
     * @param number the number to align
     * @return the hole number if close enough, and the given number itself if not.
     */
    public static double alignHoleNumber(double number) {

        int round = (int) Math.round(number);

        return isZero(number - round) ? round : number;
    }

    /**
     * Check whether two numbers have the same sign
     *
     * @param n1 1st number
     * @param n2 2nd number
     * @return true if the numbers have the same sign
     */
    public static boolean checkSign(double n1, double n2) {
        return (n1 < 0 && n2 < 0) || (n1 > 0 && n2 > 0);
    }

    /**
     * Provide a real random number in range between min and max
     *
     * @param min value (included)
     * @param max value (excluded)
     * @return the random value
     */
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    static int DIGITS = 7;

    public static double alignDouble(double d){
        double scale = Math.pow(10, DIGITS);
        return Math.round(d * scale) / scale;
    }

    public static Complex alignComplex(Complex complex) {
        return new Complex(alignDouble(complex.getReal()), alignDouble(complex.getImaginary()));
    }

    public static boolean isZeroComplex(Complex complex) {
        return isZero(complex.getReal()) && isZero(complex.getImaginary());
    }

    /*
    https://en.wikipedia.org/wiki/Quartic_function
     */
    public static List<Complex> solve4degreeQuarticFunction(double a, double b, double c, double d, double e) {
        double p = (8.0 * a * c - 3 * b * b) / (8 * a * a);
        double q = (b * b * b - 4.0 * a * b * c + 8 * a * a * d) / (8 * a * a * a);

        double det0 = c * c - 3 * b * d + 12 * a * e;
        double det1 = 2 * c * c * c - 9 * b * c * d + 27 * b * b * e + 27 * a * d * d - 72 * a * c * e;

        List<Complex> Qs = new Complex(det1 * det1 - 4 * det0 * det0 * det0).sqrt().add(det1).divide(2).nthRoot(3);
        Complex S, Q;
        int indexQ = 0;
        do {
            Q = Qs.get(indexQ);
            S = Q.add(new Complex(det0).divide(Q)).divide(3 * a).add(-(2.0 / 3) * p).sqrt().multiply(0.5);
            indexQ++;
        } while (isZeroComplex(S) && indexQ <= Qs.size());

        Complex firstPart = new Complex(-b / (4 * a));
        Complex f = S.pow(2).multiply(-4).subtract(2 * p);
        Complex l = new Complex(q).divide(S);
        Complex lastPart12 = f.add(l).sqrt().multiply(0.5);
        Complex lastPart34 = f.subtract(l).sqrt().multiply(0.5);

        Complex sol1 = firstPart.subtract(S).add(lastPart12);
        Complex sol2 = firstPart.subtract(S).subtract(lastPart12);
        Complex sol3 = firstPart.add(S).add(lastPart34);
        Complex sol4 = firstPart.add(S).subtract(lastPart34);

        return Arrays.asList(sol1, sol2, sol3, sol4)
                .stream()
                .map(complex -> alignComplex(complex))
                .collect(Collectors.toList())
                ;
    }


}
