package ru.job4j.io.echoserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * EchoServer receives local http requests and sends echo-answers (to the local host too).
 * Uses "Exit" or "exit" to stop execution.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class EchoServer {
    /**
     * Main method.
     * @param args - command line arguments are not provided.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean exit = false;
            while (!exit) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String msg = EchoServer.parseMsg(str);
                    if (msg != null && (msg.equals("Exit") || msg.equals("exit"))) {
                        exit = true;
                    }
                    while (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }
                    System.out.println("------------------------------------------");
                    System.out.println(out.toString());
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (msg != null) {
                        if (msg.equals("hello") || msg.equals("Hello")) {
                            out.write("Hello, my dear friend".getBytes());
                        } else {
                            out.write(msg.getBytes());
                        }
                    }
                    System.out.println("------------------------------------------");
                }
            }
        }
    }

    /**
     * Parses the message from http-request string.
     * @param str - http-request string.
     * @return - parsed message.
     */
    private static String parseMsg(String str) {
        String msg = null;
        String subStr = "?msg=";
        int position = str.indexOf(subStr) + subStr.length();
        if (str != null && position != subStr.length() - 1) {
            msg = str.substring(position);
            int space = msg.indexOf(" ");
            if (space != -1) {
                msg = msg.substring(0, space);
            }
        }
        return msg;
    }
}