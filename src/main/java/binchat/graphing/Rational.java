package binchat.graphing;

import java.util.ArrayList;
import java.util.List;

public class Rational {
    Polynomial numerator, denominator;

    public Rational(Polynomial num, Polynomial den) {
        this.numerator = num;
        this.denominator = den;
    }

    public double evaluate(double x) {
        double num = numerator.evaluate(x);
        double den = denominator.evaluate(x);
        if (den == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return num / den;
    }

    public boolean hasHorizontalAsym() {
        if (this.numerator.getHighestPower() - 1 >= this.denominator.getHighestPower()) {
            return false;
        }
        return true;
    }

    public Double getHorizontalAsym() {
        if (this.numerator.getHighestPower() + 1 <= this.denominator.getHighestPower()) {
            return 0.0D;
        } else if (this.hasHorizontalAsym())
            return this.numerator.getTerm(0) / this.denominator.getTerm(0);
        else {
            return Double.NaN;
        }
    }

    public List<Double> getVerticleAsym() {
        ArrayList<Double> asym = new ArrayList<Double>();
        for(Double root : this.denominator.getRealRoots()) {
            asym.add(-root);
        }
        return asym;
    }

}
