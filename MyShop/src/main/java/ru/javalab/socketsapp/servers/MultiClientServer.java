package ru.javalab.socketsapp.servers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javalab.socketsapp.models.User;
import ru.javalab.socketsapp.repositories.CrudImpl;
import ru.javalab.socketsapp.repositories.CrudInterface;
import ru.javalab.socketsapp.services.AuthService;
import ru.javalab.socketsapp.services.AuthServiceInteface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MultiClientServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private int freeIndex = 0;
    private int freeIndexToConnect;

    public MultiClientServer() {
        clients = new ArrayList<ClientHandler>();
    }

    public void start(int port, String ipAddressServer) {
        try {
            serverSocket = new ServerSocket(port, 1500, InetAddress.getByName(ipAddressServer));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for (; ; ) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler handler =
                        new ClientHandler(socket);
                handler.start();
            } catch (SocketTimeoutException exception) {

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        private Long clientId;
        private String clientName;
        private Socket clientSocket;
        private BufferedReader reader;
        private int handlerId = 0;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(handlerId, this);
        }

        @Override
        public void run() {
            try {
                AuthServiceInteface auth = new AuthService();
                ObjectMapper mapper = new ObjectMapper();
                BufferedReader reader = new BufferedReader(new InputStreamReader(clients.get(handlerId).clientSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(clients.get(handlerId).clientSocket.getOutputStream(), true);
                handlerId++;
                String jsonOnConnect = reader.readLine();
                User user = mapper.readValue(jsonOnConnect, User.class);
                String login = user.getLogin();
                CrudInterface crudInterface = new CrudImpl();
                Optional<User> optionalUser = crudInterface.checkUser(user);
                if (optionalUser.isPresent()) {
                    System.out.println("found");
                    this.clientName = login;
                    clientId = optionalUser.get().getId();
                    printWriter.println("Вы подключены");
                    System.out.println("Подключился пользователь : " + clientName);
                } else {
                    if (crudInterface.saveUser(user)) {
                        System.out.println("notfound");
                        this.clientName = login;
                        Optional<User> newUser = crudInterface.checkUser(user);
                        clientId = newUser.get().getId();
                        printWriter.println("Вы зарегистрированы");
                        System.out.println("Зарегистрирован новый пользователь: " + clientName);
                    } else {
                        printWriter.println("Логин или пароль введены неверно либо сущевствуют");
                        printWriter.println("Вы будете отключены");
                        clientSocket.close();
                    }
                }
            } catch (IOException | SQLException e) {
                try {
                    clientSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }


            String line;
            try {
                CrudInterface crudInterface = new CrudImpl();
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    if (line.equals("/end")) {
                        System.out.println("Пользователь отключился : " + clientName);
                        clients.remove(handlerId);
                        handlerId--;
                        reader.close();
                        clientSocket.close();
                    }
                    crudInterface.saveMessageWithId(clientId, line);
                    System.out.println(clientId + " " + clientName + ": " + line);
                    for (ClientHandler client : clients) {
                        PrintWriter writer = new PrintWriter(
                                client.clientSocket.getOutputStream(), true);
                        writer.println("[" + clientName + "]" + " " + line);
                    }
                }
                System.out.println("Пользователь откл : " + clientName);

                clients.remove(handlerId);
                handlerId--;
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
