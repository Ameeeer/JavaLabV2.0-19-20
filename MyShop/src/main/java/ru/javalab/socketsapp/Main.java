package ru.javalab.socketsapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javalab.socketsapp.models.User;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setLogin("onsdfnawif");
        user.setPassword("onsdfnawiasd");
        String json  = objectMapper.writeValueAsString(user);
        JsonNode jsonNode= objectMapper.readValue(json,JsonNode.class);
        System.out.println(jsonNode.get("login").asText());
        System.out.println(jsonNode.get("password").asText());
        System.out.println(json);
        User user1 = objectMapper.readValue(json,User.class);
    }
}
