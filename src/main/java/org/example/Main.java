package org.example;

import java.io.IOException;
import java.net.ServerSocket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(2345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}