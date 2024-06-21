package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private Map<String, ClientHandler> handlers = new HashMap<>();

    public void start(int port) throws IOException {
        System.out.println("Server started");
        serverSocket=new ServerSocket(port);
        while(true)
        {
            Socket socket = serverSocket.accept();
            ClientHandler handler= new ClientHandler(socket, this);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }

    public void addClient(String login, ClientHandler handler)
    {
        handlers.put(login, handler);
        printLogins();
    }

    public void removeClient(String login)
    {
        handlers.remove(login);
    }

    public void broadcast(String message)
    {
        for(ClientHandler handler : handlers.values())
        {
            //System.out.println(message);
            handler.send(message);
        }
    }

    public void printLogins()
    {
        System.out.println(handlers.keySet());
    }
}
