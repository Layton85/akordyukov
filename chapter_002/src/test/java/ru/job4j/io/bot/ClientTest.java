package ru.job4j.io.bot;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Client Test.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ClientTest {
    private final PrintStream stdout = System.out;
    private final InputStream stdin = System.in;

    /** helper buffer in memory for output. */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenUserInputThenClientPassItToTheSocketOutput() throws IOException {
        System.setIn(new ByteArrayInputStream("exit".getBytes()));
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(this.stdin);
        Client client = new Client(socket);
        client.start();
        String result = out.toString();
        System.setIn(this.stdin);
        assertThat(result, is("exit" + System.lineSeparator()));
    }

    @Test
    public void whenClientSocketReceiveMsgThenClientWriteIt() throws IOException {
        System.setOut(new PrintStream(this.out));
        StringJoiner userInput = new StringJoiner(System.lineSeparator());
        userInput.add("hello");
        userInput.add("exit");
        System.setIn(new ByteArrayInputStream(userInput.toString().getBytes()));
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("Hello, my dear friend, I'm an oracle.".getBytes()));
        when(socket.getOutputStream()).thenReturn(this.stdout);
        Client client = new Client(socket);
        client.start();
        String result = this.out.toString();
        System.setOut(this.stdout);
        System.setIn(this.stdin);
        assertThat(result, is("Hello, my dear friend, I'm an oracle." + System.lineSeparator()));
    }
}