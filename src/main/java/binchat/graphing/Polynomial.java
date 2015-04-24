package binchat.graphing;

import java.awt.*;

public class Polynomial {
    double[] terms;
    String equation;
    public Polynomial(double[] terms, String equation){
        this.terms = terms;
        this.equation = equation;
    }
    public double evaluate(double x){
        double output = 0;
        for (int i = 0; i < this.terms.length; i++) {
            output += terms[i]*Math.pow(x,i);
        }
        return output;
    }

}
