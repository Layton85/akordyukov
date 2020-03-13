package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client - client side of the bot.
 * Client reads user`s input and sends it to Server (using socket).
 * Also Client receives and writes Server answers.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {
    /** Client socket */
    private final Socket socket;

    /**
     * Constructor.
     * @param socket - specified socket.
     */
    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts client side of the bot.
     * @throws IOException
     */
    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request = "";
        String response = "";
        do {
            request = console.nextLine();
            out.println(request);
            if (!"exit".equals(request)) {
                response = in.readLine();
                while (response != null && !response.isEmpty()) {
                    System.out.println(response);
                    response = in.readLine();
                }
            }
        } while (!"exit".equals(request));
    };

    /**
     * Main method.
     * @param args - the arguments are not provided yet.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Client(new Socket(InetAddress.getByName("localhost"), 1111)).start();
    }
}
