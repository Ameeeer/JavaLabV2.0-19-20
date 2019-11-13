package ru.javalab.socketsapp.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javalab.socketsapp.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String jsonUser;
    private ObjectMapper mapper = new ObjectMapper();

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiveMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String createJSON(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        try {
            String jsonCreate = mapper.writeValueAsString(user);
            jsonUser = jsonCreate;
            return jsonCreate;
        } catch (JsonProcessingException e) {
            // TODO: 12.11.2019 Add exception on create json value
            return null;
        }
    }

    public String jsonMessage(String login, String message) {
        User user = new User();
        user.setLogin(login);
        user.setMessage(message);
        try {
            String jsonCreate = mapper.writeValueAsString(user);
            jsonUser = jsonCreate;
            return jsonUser;
        } catch (JsonProcessingException e) {
            // TODO: 12.11.2019 Add exception on create json value
            return null;
        }
    }

    public void sendMessage(String message) {
        writer.println(message.trim());
    }

    private Runnable receiveMessagesTask = new Runnable() {
        public void run() {
//            try {
//                User user = mapper.readValue(reader.readLine(), User.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            while (true) {
                try {
                    String message = reader.readLine();
                    if (message.equals(null)) {
                        message = "null";
                    }
                    System.out.println(message);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

}









