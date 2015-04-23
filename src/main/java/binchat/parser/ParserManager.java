package binchat.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserManager {

    public void parseChatLine(String chat_line) {
        if (chat_line.substring(0, 1).equals("/")) {
            chat_line = chat_line.substring(1, chat_line.length());
            commandParser(chat_line);
        } else {
            //send to chat parser
        }
    }

    // after the slash are removed
    public void commandParser(String chat_line) {
        int endWord = chat_line.indexOf(" ");
        String command = chat_line.substring(0, endWord).toLowerCase()
                .replace("disconnect", "kick").replace("calculate", "evaluate");
        if (command.equals("kick")) {

        } else if (command.equals("graph")) {

        } else if (command.equals("factor")) {

        } else if (command.equals("evaluate")) {

        } else if (command.equals("help")) {

        } else {

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
    public void mathParser(String chat_line) {
        chat_line = chat_line.toLowerCase().replace(" ", "");
        List<Double> terms = new ArrayList<Double>();
        while (chat_line.contains("x")) {
            if (chat_line.substring(0, 1).equals("+"))
                chat_line = chat_line.substring(1, chat_line.length()); // removes + in front of coefficient
            int x_index = chat_line.indexOf("x");
            if (chat_line.substring(x_index + 1, x_index + 2).equals("^")) {
                int degree_index = x_index + 2;
                int degree = Integer.parseInt(chat_line.substring(degree_index, degree_index + 1)); // This as it is written, assumes degree less than 10
                terms.add(degree, Double.parseDouble(chat_line.substring(0, x_index)));
                chat_line = chat_line.substring(degree_index + 1, chat_line.length());
            }
        }
    }
}
