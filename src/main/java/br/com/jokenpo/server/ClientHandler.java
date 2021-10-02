package br.com.jokenpo.server;

import br.com.jokenpo.enumeration.JokenpoEnum;
import br.com.jokenpo.service.JokenpoService;
import br.com.jokenpo.util.ConnectionUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientHandler extends ConnectionUtil implements Runnable {

    private JokenpoService jokenpoService = new JokenpoService();
    private ClientHandler[] clients;

    public ClientHandler(BufferedReader reader, BufferedWriter writer, ClientHandler[] clients) {
        this.bufferedReader = reader;
        this.bufferedWriter = writer;
        this.clients = clients;
    }

    @Override
    public void run() {
        boolean end = Boolean.FALSE;
        try {
            while (true) {

                String clientMessage = bufferedReader.readLine();
                JokenpoEnum messageJokenpoMoveEnum = null;

                for (int index = 0; index < 2; index++) {

                    if (this.clients[index] != null) {
                        clients[index].bufferedWriter.write(clientMessage);
                        clients[index].bufferedWriter.newLine();
                        clients[index].bufferedWriter.flush();
                        messageJokenpoMoveEnum = JokenpoEnum.toEnum(clientMessage);

                        if (messageJokenpoMoveEnum != null) {
                            //insere jogada de um cliente
                            this.clients[index].jokenpoService.setEntry(messageJokenpoMoveEnum);

                            //verifica se ambos os jogadores já fizeram a jogada
                            if (this.clients[index].jokenpoService.getEntriesSize() == 2) {
                                int[] result = this.clients[index].jokenpoService.play();

                                switch (result[0]) {
                                    case -1:
                                        this.clients[1].bufferedWriter.write("Vitória! " + JokenpoEnum.toMoveName(result[2])
                                                + " X " + JokenpoEnum.toMoveName(result[3]));
                                        this.clients[0].bufferedWriter.write("Você perdeu! " + JokenpoEnum.toMoveName(result[2])
                                                + " X " + JokenpoEnum.toMoveName(result[3]));
                                        break;
                                    case 0:
                                        this.clients[index].bufferedWriter.write("Empate! " + JokenpoEnum.toMoveName(result[2])
                                                + " X " + JokenpoEnum.toMoveName(result[3]));
                                        break;
                                    case 1:
                                        this.clients[1].bufferedWriter.write("Você perdeu! " + JokenpoEnum.toMoveName(result[2])
                                                + " X " + JokenpoEnum.toMoveName(result[3]));
                                        this.clients[0].bufferedWriter.write("Vitória! " + JokenpoEnum.toMoveName(result[2])
                                                + " X " + JokenpoEnum.toMoveName(result[3]));
                                        break;
                                }
                            }
                            this.clients[index].bufferedWriter.newLine();
                            this.clients[index].bufferedWriter.flush();
                        }
                    }
                }

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
            ioe.printStackTrace();
        }
    }
}
