package br.com.jokenpo.client;

import br.com.jokenpo.util.ConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;

public class Messaging extends ConnectionUtil implements Runnable{

    public Messaging(BufferedReader reader) {
        bufferedReader = reader;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException ioe) {
            this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava enviando mensagem ", ioe);
            ioe.printStackTrace();
        }
    }
}
