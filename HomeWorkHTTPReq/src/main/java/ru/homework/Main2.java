package ru.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Main2 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getByName("skilltoyshop.ru"), 80);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.println("GET " + "/category/fingerboard" + " HTTP/1.1");
        pw.println("Host: " + "skilltoyshop.ru");
        pw.println("");
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;
        StringBuilder st = new StringBuilder();
        while ((t = br.readLine()).equals("<!DOCTYPE html>")) {
            st.append(t);
        }
        while (!(t = br.readLine()).equals("</html>")) {
            st.append(t);
        }
        br.close();
        System.out.println(st.toString());
    }
}
