package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server - server side of the bot.
 * Server answers to the Client questions and sends it to the socket.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {
    /** Server socket */
    private final Socket socket;

    /**
     * Constructor.
     * @param socket - specified socket.
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts the server side of the bot.
     * @throws IOException
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String request = "";
        String response = "";
        do {
            System.out.println("wait command ...");
            request = in.readLine();
            System.out.println(request);
            if ("hello".equals(request)) {
                response = "Hello, my dear friend, I'm an oracle." + System.lineSeparator();
                out.println(response);
            } else if (!"exit".equals(request)) {
                response = "I don`t understand" + System.lineSeparator();
                out.println(response);
            }
        } while (!"exit".equals(request));
    }

    /**
     * Main method.
     * @param args - the arguments are not provided yet.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(1111).accept()) {
            new Server(socket).start();
        }
    }
}
