package binchat.parser;

import binchat.graphing.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class ParserManager {
    public void parseChatLine(String chat_line){
        if(chat_line.substring(0,1).equals("/")){
            chat_line = chat_line.substring(1,chat_line.length());
            commandParser(chat_line);
        }
        else {
            //send to chat parser
        }
    }
    // after the slash are removed
    public void commandParser(String chat_line){
        int endWord = chat_line.indexOf(" ");
        String command = chat_line.substring(0, endWord).toLowerCase()
                .replace("disconnect", "kick").replace("calculate", "evaluate");
        if(command.equals("kick")){

        }
        else if(command.equals("graph")){
            mathParser(chat_line.substring(5,chat_line.length()));
        }
        else if(command.equals("factor")){

        }
        else if(command.equals("evaluate")){

        }
        else if(command.equals("help")){
            System.out.println(
                            "/calculate will evaluate a function, given the form f(3)= or f(x)= x^2 + 4x + 4 3\n" +
                            "/disconnect\n" +
                            "/evaluate\n" +
                            "/factor\n" +
                            "/help\n" +
                            "/kick");
        }
        else{

        }

        /*
        kick / disconnect
        graph
        factor
        evaluate / calculate
        help
         */
    }
    // removing command

    public Polynomial mathParser(String chat_line){
        // Parses a polynomial of the form
        chat_line = chat_line.toLowerCase().replace(" ", "").replace("y=", "");
        String equation = "y = "+chat_line;
        List<Double> terms = new ArrayList<Double>(); // will have a list of all coefficients
        terms.add(0,0.0);
        terms.add(0,0.0); // populates the basic polynomial, a binomial
        while(chat_line.contains("x")){ // while it has x terms
            System.out.println(chat_line);
            if(chat_line.substring(0,1).equals("+")) chat_line = chat_line.substring(1,chat_line.length()); // removes + in front of coefficient
            int x_index = chat_line.indexOf("x");
            double coefficent;
            if (x_index == 0) coefficent = 1;
            else coefficent = Double.parseDouble(chat_line.substring(0, x_index));

            if(chat_line.substring(x_index+1,x_index+2).equals("^")) { // if after the x there is a caret
                int degree_index = x_index + 2;
                int end_index = degree_index;
                while (end_index<chat_line.length()){ // will scan the string until it reaches a + or - sign (this is for degrees of 10 or higher)
                    if (chat_line.charAt(end_index) == '+' || chat_line.charAt(end_index) == '-'){
                        break;
                    }
                    end_index ++;
                }
                int degree = Integer.parseInt(chat_line.substring(degree_index, end_index)); // This as it is written, assumes degree less than 10
                if(degree+1 > terms.size()){
                    int new_terms = degree+1 -(terms.size());
                    for (int i = 0; i < new_terms; i++) {
                        terms.add(terms.size()-1,0.0);
                    }
                }
                terms.set(degree, terms.get(degree) +coefficent);
                chat_line = chat_line.substring(end_index, chat_line.length()); // shortens the string starting at the +/- sign
            }
            else{
                // If it does not have a caret, it is of degree 1
                terms.set(1, terms.get(1) + coefficent);
                if(x_index+1 != chat_line.length()) chat_line = chat_line.substring(x_index+1,chat_line.length());
                else chat_line = "";
            };
        }
        // once it finishes parsing the x terms, we need to add the constant term, represented as the zeroith degree
        chat_line.replace("+", "").replace(" ", "");
        if(chat_line.length()>0) terms.set(0, terms.get(0) + Double.parseDouble(chat_line));
        System.out.println(terms);
        // convert it to an array
        double[] output = new double[terms.size()];
        for (int i = 0; i < terms.size(); i++) {
            output[i] = terms.get(i);
        }
        Polynomial p = new Polynomial(output,equation);
        return p;
    }
}