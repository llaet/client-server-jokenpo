package br.com.jokenpo.client;

import br.com.jokenpo.util.ConnectionUtil;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class Client extends ConnectionUtil {

    public Client() {
        super.setUp();
    }

    public void start() {
        try {
            //cria socket de comunicação entre cliente e servidor
            socket = new Socket("localhost", HOST_ADDRESS);

            //estabelece e escuta comunicação com servidor
            listen();

            Messaging messaging = new Messaging(bufferedReader);
            new Thread(messaging).start();

            while (true) {
                String message = scanner.nextLine();

                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (IOException ioe) {
            this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava enviando mensagem ", ioe);
            ioe.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
                if (outputWriter != null) {
                    outputWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException ioe) {}
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }


}
