package binchat.graphing;

/**
 * Created by Kevin on 2015-04-27.
 */
public class Rational {
    Polynomial numerator, denominator;
    public Rational(Polynomial num, Polynomial den){
        this.numerator = num;
        this.denominator = den;
    }
    public double evaluate(double x){
        double num = numerator.evaluate(x);
        double den = denominator.evaluate(x);
        if (den == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return num/den;
    }
}
