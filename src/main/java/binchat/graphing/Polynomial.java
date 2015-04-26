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

}
