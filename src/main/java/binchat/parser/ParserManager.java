package binchat.parser;

import binchat.graphing.Polynomial;
import binchat.graphing.TemporaryWindow;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ParserManager {
    TemporaryWindow temporaryWindow;
    public ParserManager(){
        temporaryWindow = new TemporaryWindow();
        temporaryWindow.initialize();
    }
    public void parseChatLine(String chat_line){
        if(chat_line.length()>0){
            if(chat_line.substring(0,1).equals("/")){
                chat_line = chat_line.substring(1,chat_line.length());
                commandParser(chat_line);
            }
            else {
                System.out.println("SENDING MESSAGE: " + chat_line);
                System.out.println(". . ."); // send message to server
                System.out.println("Message not sent."); // if fails, print message not sent
            }
        }
    }
    // after the slash are removed
    public void commandParser(String chat_line){
        if (chat_line.length()!=0){
            int endWord = chat_line.indexOf(" ");
            if (endWord==-1) endWord = chat_line.length();
            String command = chat_line.substring(0, endWord).toLowerCase()
                    .replace("kick", "disconnect").replace("calculate", "evaluate");
            if(command.equals("graph")){
                boolean graphable = true;
                chat_line = chat_line.replace(" ","");
                if(chat_line.length()>5){
                    if(chat_line.contains(",")){
                        int currentIndex = 0;
                        int[] commas = new int[4];
                        double[] screenCoordinates = new double[4];
                        for (int i = 0; i < commas.length; i++) {
                            commas[i] = chat_line.indexOf(",", currentIndex);
                            if(commas[i] == -1){
                                System.out.println("ERROR in graphing. Looking for 5 parameters, found " + (1+i));
                                graphable = false;
                                break;
                            }
                            currentIndex = commas[i]+1;
                        }
                        if (graphable){
                            for (int i = 0; i < screenCoordinates.length-1; i++) {
                                screenCoordinates[i] = Double.parseDouble(chat_line.substring(commas[i]+1,commas[i+1]));
                            }
                            screenCoordinates[screenCoordinates.length-1] = Double.parseDouble(chat_line.substring(commas[commas.length-1]+1,chat_line.length()));
                            temporaryWindow.display(mathParser(chat_line.substring(5,commas[0])),screenCoordinates[0],screenCoordinates[1],screenCoordinates[2],screenCoordinates[3]);
                        }
                    }
                }
                else{
                    System.out.println("ERROR in graphing. Looking for 5 parameters, found 0.");
                }

            }
            else if(command.equals("disconnect")){
                System.out.println("CLOSING GRAPHING WINDOW.");
                temporaryWindow.close();
            }
            else if(command.equals("factor")){

            }
            else if(command.equals("evaluate")){

            }
            else if(command.equals("help")){
                System.out.println(
                        "****************HELP****************\n" +
                        "Type a slash as the first character to denote that you want to use a function, then " +
                        "type the function name and then a space. Finally, give it the necessary parameters, delimited by commas. " +
                        "The following is an example to graph a polynomial:\n" +
                        "/graph x^8+ 8x^7 - 111x^6 -792x^5 + 4371x^4 + 23520x^3 -70117x^2-192080x + 235200,-9,9,-350000,900000\n" +
                        "This will graph the polynomial from x=-9 to x=9.\n" +
                        "/add\t polynomial,polynomial" +
                        "/calculate\t will evaluate a function, given the form f(3)= or f(x)= x^2 + 4x + 4 3\n" +
                        "/disconnect\t will close the window and end the chat.\n" +
                        "/evaluate\t\n" +
                        "/factor\t\n" +
                        "/graph\t will graph the function\n" +
                        "/help\t you are looking at it now! Displays a list of all functions and their descriptions");
            }
            else{
                System.out.println("Command \"" + command + "\" not recognized! Please refer to /help to get a list of all functions.");
            }
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
        return new Polynomial(output);
    }
}