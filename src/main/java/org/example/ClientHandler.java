package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable
{
    private String login;
    private Socket socket;
    private Server server;

    private Scanner input;

    private PrintWriter output;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.input = new Scanner(socket.getInputStream());
        this.output =new PrintWriter(socket.getOutputStream(), true);
    }



    public void send(String message)
    {
        output.println(message);
    }


    @Override
    public void run() {
        login =input.nextLine();
        server.addClient(login, this);
        server.broadcast("Server: " + login + " connected");
        String message;
        do {
            message=input.nextLine();
            server.broadcast(login+": "+message);
        }while (!message.equals("Bye"));
        server.removeClient(login);
        server.broadcast("Server: " + login + " disconnected");
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
