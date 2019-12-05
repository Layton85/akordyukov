package ru.job4j.serveravailability;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Analysis - class for Analysing server logs.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Analysis {
    /**
     * The method is looking for a server unavailability intervals in source logs and write it in a target log.
     * @param source - path of the source log file.
     * @param target - path of the target log file.
     */
    public void unavailable(String source, String target) {
        boolean unavailable = false;
        String str;
        StringBuilder intervals = new StringBuilder();
        String lastTime = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while ((str = reader.readLine()) != null) {
                List<String> lexems = Arrays.asList(str.split(" "));
                    String command = lexems.get(0);
                    String time = lexems.get(1);
                    if (!unavailable) {
                        if (command.equals("400") || command.equals("500")) {
                            unavailable = true;
                            intervals.append(time + ";");
                        }
                    } else {
                        if (command.equals("200") || command.equals("300")) {
                            unavailable = false;
                            intervals.append(time + System.lineSeparator());
                        } else {
                            lastTime = time;
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (unavailable) {
            intervals.append(lastTime);
        }
        writeIntervals(intervals.toString(), target);
    }

    /**
     * Helper method. It`s write unavailability intervals in a target log.
     * @param intervals - unavailability intervals/
     * @param target - target log file name.
     */
    private void writeIntervals(String intervals, String target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            writer.write(intervals);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * main method.
     * @param args - command line args.
     */
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
