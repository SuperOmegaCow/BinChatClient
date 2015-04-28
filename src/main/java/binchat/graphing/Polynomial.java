package binchat.graphing;

import binchat.network.logic.State;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    double[] terms;
    double remainder;
    private static final double INTERVAL = 0.0001;
    private static final double RANGE = 100;

    public Polynomial(double[] terms) {
        this.terms = terms;
        this.remainder = 0;
    }

    public double getTerm(int x) {
        return terms[x];
    }

    public int getHighestPower() {
        return this.terms.length - 1;
    }

    // given an x value, evaluate the function
    public double evaluate(double x) {
        double output = 0;
        for (int i = 0; i < this.terms.length; i++) {
            output += terms[i] * Math.pow(x, i);
        }
        return output;
    }

    // calculates the derivative of a function
    public Polynomial derivative() {
        double[] new_terms = new double[this.terms.length - 1];
        for (int i = 0; i < new_terms.length; i++) {
            new_terms[i] = this.terms[i + 1] * (i + 1);
        }
        return new Polynomial(new_terms);
    }

    // returns a string for the function
    public String getEquation() {
        DecimalFormat df = new DecimalFormat("#.##");
        String ret = "";
        for (int i = this.terms.length - 1; i >= 0; i--) {
            if (this.terms[i] > 0) ret = ret + "+" + df.format(this.terms[i]);
            else if (this.terms[i] == 0) continue;
            else ret = ret + df.format(this.terms[i]);
            if (i == 1) ret = ret + "x";
            else if (i != 0) ret = ret + "x^" + i;
        }
        if (ret.charAt(0)=='+')ret = ret.substring(1,ret.length());
        ret = "y=" + ret;
        return ret;
    }

    // adds the polynomial with another polynomial
    public Polynomial add(Polynomial other) {
        int iterations;
        double[] new_terms;
        boolean other_is_longer;
        if (this.terms.length > other.terms.length) {
            other_is_longer = false;
            iterations = other.terms.length;
            new_terms = new double[this.terms.length];
        } else {
            other_is_longer = true;
            iterations = this.terms.length;
            new_terms = new double[other.terms.length];
        }
        for (int i = 0; i < iterations; i++) {
            new_terms[i] = this.terms[i] + other.terms[i];
        }
        if (other_is_longer) {
            for (int i = iterations; i < new_terms.length; i++) {
                new_terms[i] = other.terms[i];
            }
        } else {
            for (int i = iterations; i < new_terms.length; i++) {
                new_terms[i] = this.terms[i];
            }
        }
        return new Polynomial(new_terms);
    }

    // subtracts a polynomial from the current polynomial
    public Polynomial subtract(Polynomial other) {
        for (int i = 0; i < other.terms.length; i++) {
            other.terms[i] = -other.terms[i];
        }
        return this.add(other);
    }

    // multiplies this polynomial by another
    public Polynomial multiply(Polynomial other) {
        int degree = this.terms.length + other.terms.length - 1;
        double[] new_terms = new double[degree];
        // populate new_terms with 0s
        for (int i = 0; i < new_terms.length; i++) {
            new_terms[i] = 0;
        }
        for (int i = 0; i < this.terms.length; i++) {
            for (int j = 0; j < other.terms.length; j++) {
                int current_degree = i + j;
                new_terms[current_degree] += (this.terms[i] * other.terms[j]);
            }
        }
        return new Polynomial(new_terms);
    }

    // returns empty array if no real roots
    public double[] factorQuadratic() {
        double[] factors = new double[2];
        double sqrt = Math.sqrt(Math.pow(this.terms[1], 2) - 4 * this.terms[2] * this.terms[3]);
        if (sqrt >= 0) {
            factors[0] = (-this.terms[1] + sqrt) / (2 * this.terms[2]);
            factors[1] = (-this.terms[1] - sqrt) / (2 * this.terms[2]);
        }
        return factors;
    }

    public Rational divide(Polynomial other) {
        return new Rational(this, other);
    }

    /*public Polynomial divide(Polynomial other){
        double[] new_terms = new double[this.terms.length-other.terms.length+1];
        double[] temp = this.terms;
        for (int i = 1; i <= new_terms.length; i++) {
            double coeff = Math.floor(this.terms[this.terms.length-i] / other.terms[other.terms.length-1]);
        }

    }
    */
    
    public ArrayList<Double> getRoots() {
        double ACCURACY = 0.01D;
        ArrayList<Double> roots = new ArrayList<Double>();
        ArrayList<Double> refined = new ArrayList<Double>();
        for (double i = -RANGE; i < RANGE; i += INTERVAL) {
            double y = this.evaluate(i);
            if (y > -ACCURACY && y < ACCURACY) {
                roots.add(i);
            }
        }
        //add a placeholder. this will never be checked by the loop and signifies the end of a loop.
        roots.add(3.1415926535);
        System.out.println(roots);
        double counter = 0;
        double total = 0;
        // the estimated roots will be in series of x values.
        // this will average the x values to get a more refined result, so as to not double count roots.
        // note this is not by any means a perfect method, as it can be skewed for result non symmetrical functions.
        for (int i = 0; i < roots.size()-1; i++) {
            if (roots.get(i) + INTERVAL != roots.get(i + 1)) {
                counter ++;
                total += roots.get(i);
                refined.add((double)Math.round((total/counter)*100)/100);
                counter = 0;
                total = 0;
            }
            else{
                counter++;
                total += roots.get(i);
            }
        }
        return refined;
    }

}
