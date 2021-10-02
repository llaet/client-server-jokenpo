package br.com.jokenpo.client;

import br.com.jokenpo.util.ConnectionUtil;
import br.com.jokenpo.view.JokenpoClientPane;

import java.io.BufferedReader;
import java.io.IOException;
//import java.util.logging.Level;

public class Messaging extends ConnectionUtil implements Runnable{
    
    private JokenpoClientPane pane;

    public Messaging(BufferedReader reader, JokenpoClientPane pane) {
        bufferedReader = reader;
        this.pane = pane;
    }

    @Override
    public void run() {
        try {
        	while(true) {
        		String returnText = bufferedReader.readLine();
                
                if (returnText.contains("Você perdeu!") || returnText.contains("Vitória!") || returnText.contains("Empate!")) {
                	pane.showResultJokenpoPane(returnText);                
                }
        	}
        } catch (IOException ioe) {
            //this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto estava enviando mensagem ", ioe);
            ioe.printStackTrace();
        }
    }
}
