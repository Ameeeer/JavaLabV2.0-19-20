package ru.JavaServlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class Logger implements LoggerInterf {
    private Date date;
    private PrintWriter printWriter;

    public Logger() throws FileNotFoundException {
        printWriter = new PrintWriter(new FileOutputStream("text.txt"));
        date = new Date();
    }

    public void log(String method, String urlPath) {
        printWriter.println(date.getTime() + " " + method + " " + urlPath);
        printWriter.close();
    }
}
