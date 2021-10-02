package br.com.jokenpo.util;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class ConnectionUtil {

    protected Socket socket;
    protected InputStreamReader inputReader;
    protected OutputStreamWriter outputWriter;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;
    protected Properties props = new Properties();
    //protected final Logger LOGGER = Logger.getLogger(ConnectionUtil.class.getName());
    protected int HOST_ADDRESS;

    protected void setUp() {
        HOST_ADDRESS = 1234;
    }

    protected void listen() {
        try {
            //lê entrada e saída da comunicação
            inputReader = new InputStreamReader(socket.getInputStream());
            outputWriter = new OutputStreamWriter(socket.getOutputStream());
            //preenche os buffers com I/O da comunicação
            bufferedReader = new BufferedReader(inputReader);
            bufferedWriter = new BufferedWriter(outputWriter);

        } catch (IOException ioe) {
            //this.LOGGER.log(Level.SEVERE, "Erro de entrada/saída ocorreu enquanto " +
                //    "o serviço estava ouvindo a comunicação ", ioe);
            ioe.printStackTrace();
        }
    }
}
