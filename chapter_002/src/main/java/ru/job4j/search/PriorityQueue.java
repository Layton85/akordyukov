package ru.job4j.search;

import java.util.LinkedList;

/**
 * PriorityQueue - class emulates Queue with priority on a basic LinkedList
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    /** Linkred List of tasks */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * The method inputs Task element into the Priority Queue according to element priority.
     * If the PriorityQueue already has the element with the same priority, new element will be inserted after "elder" element
     * (like element with a lower priority).
     * @param task - Task.
     */
    public void put(Task task) {
        var index = 0;
        for (var it = this.tasks.iterator(); it.hasNext();) {
            if (task.getPriority() >= it.next().getPriority()) {
                index++;
            } else {
                break;
            }
        }
        this.tasks.add(index, task);
    }

    /**
     * The method returns (with removing) the top element of the Queue.
     * @return - the top element of the Queue.
     */
    public Task take() {
        return this.tasks.poll();
    }
}