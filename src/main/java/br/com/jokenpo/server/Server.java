package br.com.jokenpo.server;

import br.com.jokenpo.util.ConnectionUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

public class Server extends ConnectionUtil {

    private ServerSocket serverSocket;
    private ClientHandler[] clients = new ClientHandler[2];

    public Server() {
        try {
            super.setUp();
            //cria um listener para sevidor e estabelece a comunicação
            serverSocket = new ServerSocket(HOST_ADDRESS);
            serverSocket.setReuseAddress(true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void start() {

        while(true) {
            try {
                socket = serverSocket.accept();

                for(int index = 0; index < 2; index++) {

                    //estabelece e escuta comunicação com servidor
                    listen();

                    if (clients[index] == null) {
                        clients[index] =
                                new ClientHandler(bufferedReader, bufferedWriter, clients);
                        new Thread(clients[index]).start();
                        break;
                    }
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
