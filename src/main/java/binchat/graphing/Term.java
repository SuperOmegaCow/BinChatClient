package binchat.graphing;

public class Term {

    private double constant;
    private double power;

    public Term(double constant, double power) {
        this.constant = constant;
        this.power = power;
    }

    public double calculate(double x) {
        return constant * Math.pow(x, this.power);
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
