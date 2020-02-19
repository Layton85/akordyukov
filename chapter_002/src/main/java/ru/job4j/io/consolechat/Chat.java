package ru.job4j.io.consolechat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chat - Class containing all of Console Chat business logic.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Chat {
    /** Chat phrases storage */
    private List<String> phrases = new ArrayList<>();
    /** Chat log storage */
    private List<String> log = new ArrayList<>();
    /** log file path */
    private String logPath;
    /** pause word */
    private String pauseWord = "stop";
    /** continue word */
    private String continueWord = "continue";
    /** exit word */
    private String exitWord = "exit";
    /** pause flag */
    private boolean pause = false;

    /**
     * Constructor.
     * @param phrasesPath - source of chat phrases.
     * @param logPath - log file path.
     */
    public Chat(String phrasesPath, String logPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(phrasesPath))) {
            reader.lines().forEach(str -> {
                this.phrases.add(str);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.logPath = logPath;
    }

    /**
     * Answer method.
     * @return - random string from the chat phrases storage.
     */
    public String answer() {
        return this.phrases.get(new Random().nextInt(this.phrases.size() - 1));
    }

    /**
     * Ask method.
     * @param str - user`s phrase.
     * @return - chat answer.
     */
    public String ask(String str) {
        String result = null;
        if (str.equals(this.getPauseWord())) {
            this.pause = true;
        } else if (str.equals(this.getContinueWord())) {
            this.pause = false;
        } else if (!str.equals(this.getExitWord()) && !this.pause) {
            result = this.answer();
        }
        this.log.add(str);
        if (result != null) {
            this.log.add(result);
        }
        return result;
    }

    /**
     * Get-method.
     * @return - pause word.
     */
    public String getPauseWord() {
        return pauseWord;
    }

    /**
     * Get-method.
     * @return - continue word.
     */
    public String getContinueWord() {
        return continueWord;
    }

    /**
     * Get-method.
     * @return - exit word.
     */
    public String getExitWord() {
        return exitWord;
    }

    /**
     * Writes chat talk into the log file.
     */
    public void writeLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.logPath))) {
            for (String s : this.log) {
                writer.write(s + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
