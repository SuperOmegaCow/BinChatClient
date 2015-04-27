package binchat.graphing;

import java.awt.*;
import java.text.DecimalFormat;

public class Polynomial {
    double[] terms;
    public Polynomial(double[] terms){
        this.terms = terms;
    }
    public double evaluate(double x){
        double output = 0;
        for (int i = 0; i < this.terms.length; i++) {
            output += terms[i]*Math.pow(x,i);
        }
        return output;
    }
    public Polynomial derivative(){
        double[] new_terms = new double[this.terms.length - 1];
        for (int i = 0; i < new_terms.length; i++) {
            new_terms[i] = this.terms[i+1]*(i+1);
        }
        return new Polynomial(new_terms);
    }
    public String getEquation(){
        DecimalFormat df = new DecimalFormat("#.##");
        String ret = "f(x)=" + this.terms[this.terms.length-1]+"x^"+(this.terms.length-1);
        for (int i = this.terms.length-2; i >=0 ; i--) {
            if (this.terms[i]>0) ret = ret + "+" + df.format(this.terms[i]);
            else if (this.terms[i]==0) continue;
            else ret = ret + df.format(this.terms[i]);
            if (i == 1) ret = ret + "x";
            else if (i != 0) ret = ret + "x^" + i;
        }
        return ret;
    }
    public Polynomial add(Polynomial other){
        int iterations;
        double[] new_terms;
        boolean other_is_longer;
        if (this.terms.length > other.terms.length){
            other_is_longer = false;
            iterations = other.terms.length;
            new_terms = new double[this.terms.length];
        }
        else {
            other_is_longer = true;
            iterations = this.terms.length;
            new_terms = new double[other.terms.length];
        }
        for (int i = 0; i < iterations; i++) {
            new_terms[i] = this.terms[i]+other.terms[i];
        }
        if (other_is_longer){
            for (int i = iterations; i < new_terms.length; i++) {
                new_terms[i] = other.terms[i];
            }
        }
        else{
            for (int i = iterations; i < new_terms.length; i++) {
                new_terms[i] = this.terms[i];
            }
        }
        return new Polynomial(new_terms);
    }
    public Polynomial subtract(Polynomial other){
        for (int i = 0; i < other.terms.length; i++) {
            other.terms[i] = -other.terms[i];
        }
        return this.add(other);
    }
    public Polynomial multiply(Polynomial other){
        int degree = this.terms.length + other.terms.length-1;
        double[] new_terms = new double[degree];
        // populate new_terms with 0s
        for (int i = 0; i < new_terms.length; i++) {
            new_terms[i] = 0;
        }
        for (int i = 0; i < this.terms.length; i++) {
            for (int j = 0; j < other.terms.length; j++) {
                int current_degree = i+j;
                new_terms[current_degree] += (this.terms[i]*other.terms[j]);
            }
        }
        return new Polynomial(new_terms);
    }

}
