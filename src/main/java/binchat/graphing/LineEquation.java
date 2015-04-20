package binchat.graphing;

public class LineEquation {

    private Term a;
    private double b;

    public LineEquation(Term a, double b) {
        this.a = a;
        this.b = b;
    }

    public Term getA() {
        return a;
    }

    public void setA(Term a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

}
