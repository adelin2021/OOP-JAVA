package bakery.io;
//created by J.M.

import bakery.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

    public void writeLine(String text) {

        System.out.println(text);
    }
}
