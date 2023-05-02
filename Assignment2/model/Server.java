package model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Server implements Runnable {

    private int ID;
    private LinkedBlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Server(int id) {

        // initialize queue
        // LinkedBlockingQueue is Thread safe
        // Thread is blocked automatically when we try to remove from an empty queue
        // Or to add to a full queue
        tasks = new LinkedBlockingQueue<>();
        ID = id;

        // initialize waitingPeriod with 0 - AtomicInteger() implies initialization with 0
        waitingPeriod = new AtomicInteger(0);
    
    }

    public void addTask(Task newTask) {

        // add the newTask to the end of the queue
        tasks.add(newTask);
        int newTaskServiceTime = newTask.getServiceTime();

        // increment waitingPeriod with the new task's service time
        waitingPeriod.addAndGet(newTaskServiceTime);
    }

    @Override
    public void run() {
        while(currentThread().isAlive()) {
            if (!tasks.isEmpty()) {
                try {
                    // get current Task from the queue
                    Task currentTask = tasks.peek();

                    // sleep for as long as the task is running
                    // multiply by 1000 because Thread.sleep() works with milliseconds
                    int sleepTime = currentTask.getServiceTime();
                    sleep(1000 * sleepTime);

                    // remove head of the queue
                    tasks.poll();

                    // decrement the waitingPeriod with the time it took to complete the task
                    waitingPeriod.addAndGet(-sleepTime);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public String toString() {
        
        String printed = "[" + waitingPeriod.toString() + "] ";
        printed += "Server " + ID + ": ";

        if (tasks.size() > 0) {

            for(Task t : tasks) {
                
                printed += t.toString() + "; ";

            }
        } 
        else {
            
            printed += "idle";

        }

        return printed;
    }
}