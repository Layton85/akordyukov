package ru.job4j.io.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Server Test.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {
    @Test
    public void whenAskExitThenExitProgramm() throws IOException {
        this.test("exit", "");
    }

    @Test
    public void whenAskHelloExitThenReturnPhraseAndExitProgramm() throws IOException {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("hello");
        joiner.add("exit");
        this.test(joiner.toString(),
                String.format("Hello, my dear friend, I'm an oracle.%s%s",
                    System.lineSeparator(),
                    System.lineSeparator()));
    }

    @Test
    public void whenUnsupportedExpressionThenDoNotUnderstand() throws IOException {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("unsupported expression");
        joiner.add("exit");
        this.test(joiner.toString(),
                String.format("I don`t understand%s%s",
                        System.lineSeparator(),
                        System.lineSeparator()));
    }

    private void test(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(),
                is(expected));
    }
}