package binchat.graphing;

import java.util.Collection;
import java.util.List;

public class PolynomialEquation {

    private List<Term> terms;
    private Collection<Term> roots;

    public PolynomialEquation(List<Term> terms) {
        this.terms = terms;
    }

    public PolynomialEquation(Term[] terms) {
        for(Term value : terms) {
            this.terms.add(value);
        }
    }

    private void calculateTerms() {
        //TODO calculate terms and add them to list
    }

}
