package main.br.com.jokenpo.server;

import main.br.com.jokenpo.enumeration.JokenpoEnum;
import main.br.com.jokenpo.service.JokenpoService;
import main.br.com.jokenpo.util.ConnectionUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

public class Server extends ConnectionUtil {

    private ServerSocket serverSocket;
    private JokenpoService jokenpoService = new JokenpoService();

    public Server() {
        try {
            super.setUp();
            //cria um listener para sevidor e estabelece a comunicação
            serverSocket = new ServerSocket(HOST_ADDRESS);
        } catch (IOException ioe) {
            this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava iniciar o servidor ", ioe);
            ioe.printStackTrace();
        }
    }

    public void start() {
        boolean end = Boolean.FALSE;
        while(true) {
            try {
                socket = serverSocket.accept();
                //estabelece e escuta comunicação com servidor
                listen();

                while (true) {

                    String clientMessage = bufferedReader.readLine();

                    System.out.println("Cliente: " + clientMessage);

                    JokenpoEnum messageJokenpoMoveEnum = JokenpoEnum.toEnum(clientMessage);

                    if (messageJokenpoMoveEnum != null) {
                        int[] result = this.jokenpoService.play(messageJokenpoMoveEnum);

                        switch (result[1]) {
                            case -1:
                                bufferedWriter.write("Você perdeu! " + messageJokenpoMoveEnum.getMoveName()
                                        + " X " + JokenpoEnum.toMoveName(result[0]));
                                break;
                            case 0:
                                bufferedWriter.write("Empate! " + messageJokenpoMoveEnum.getMoveName()
                                        + " X " + JokenpoEnum.toMoveName(result[0]));
                                break;
                            case 1:
                                bufferedWriter.write("Vitória! " + messageJokenpoMoveEnum.getMoveName()
                                        + " X " + JokenpoEnum.toMoveName(result[0]));
                                break;
                        }
                    }

                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (end) {
                        break;
                    }
                }
                socket.close();
                inputReader.close();
                outputWriter.close();
                bufferedWriter.close();
                bufferedReader.close();

            } catch (IOException ioe) {
                this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto " +
                        "o serviço estava ouvindo a comunicação ", ioe);
                ioe.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
