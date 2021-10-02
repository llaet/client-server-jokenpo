package br.com.jokenpo.client;

import br.com.jokenpo.util.ConnectionUtil;
import br.com.jokenpo.view.JokenpoClientPane;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
//import java.util.logging.Level;

public class Client extends ConnectionUtil {
    
    private JokenpoClientPane pane;

    public Client(JokenpoClientPane pane) {
        this.pane = pane;
        super.setUp();
    }

    public void start() {
        try {
            //cria socket de comunicação entre cliente e servidor
            socket = new Socket("localhost", HOST_ADDRESS);

            //estabelece e escuta comunicação com servidor
            listen();

            Messaging messaging = new Messaging(bufferedReader,pane);
            new Thread(messaging).start();

        } catch (IOException ioe) {
            //this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava enviando mensagem ", ioe);
            ioe.printStackTrace();
        }
    }
    
    public void writeMessage(String message) {
        try {
        	Scanner scanner = new Scanner(System.in);
        	while(true) {
        		
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            message = scanner.nextLine();
        	}
        } catch (IOException ioe) {
            //this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava enviando mensagem ", ioe);
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
}
