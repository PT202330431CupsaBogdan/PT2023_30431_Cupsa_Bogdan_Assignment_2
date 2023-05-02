package business_logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import gui.*;
import model.Task;

public class SimulationManager implements Runnable {

    // data read from UI

    // maximum processing time
    private int timeLimit = -1;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int numberOfServers;
    private int numberOfClients;
    private int minArrivalTime;
    private int maxArrivalTime;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    // entity responsible for queue management and client distribution
    private Scheduler scheduler;

    // write in file
    private BufferedWriter writer;

    // frame for displaying simulation
    private SimulationFrame frame;
    private final InputFrame inputs = new InputFrame();

    // pool of tasks - clients shopping in the store
    private CopyOnWriteArrayList<Task> generatedTasks;

    public SimulationManager() {

        try {
            File file = new File("simulation.txt");
            if(file.createNewFile()){
                System.out.println("Ok");
            }else{
                System.out.println("Error");
            }
            writer = new BufferedWriter(new FileWriter(file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputs.addButtonAction(new ButtonPressed());
        inputs.viewInputFrame();

        scheduler = new Scheduler(numberOfServers, 1);
        scheduler.changeStrategy(selectionPolicy);

    }

    private CopyOnWriteArrayList<Task> generateNRandomTasks(int numberOfTasks) {

        // for thread synchronization
        CopyOnWriteArrayList<Task> tasks = new CopyOnWriteArrayList<>();
        Random random = new Random();
        // generate n random tasks
        for (int i = 0; i < numberOfTasks; i++) {

            // make sure that the random number is in the limits 
            int processingTime = random.nextInt(minProcessingTime, maxProcessingTime);
            int arrivalTime = random.nextInt(minArrivalTime, maxArrivalTime);

            tasks.add(new Task(i, arrivalTime, processingTime));
        }

        return tasks;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    @Override
    public void run() {
        generatedTasks = generateNRandomTasks(numberOfClients);
        frame = new SimulationFrame(numberOfServers, generatedTasks);
        AtomicInteger currentTime = new AtomicInteger(0);

        while (currentTime.intValue() <= timeLimit) {

            for (Task t : generatedTasks) {
                if (t.getArrivalTime() <= currentTime.intValue()) {
                    scheduler.dispatchTask(t);
                    generatedTasks.remove(t);
                }
            }

            frame.update(scheduler.getServers(), generatedTasks, currentTime.intValue());
            try {
                writer.write(generatedTasks.toString() + "\n");
                writer.write("Current time: " + currentTime.intValue() + "\n");
                writer.write(scheduler.toString());
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime.getAndIncrement();
        }
        try {

            writer.close();

        } catch (IOException e) { 

            throw new RuntimeException(e); 

        }
        
        System.exit(0);
    }

    public void getInputs(ArrayList<Integer> inputs){
        numberOfClients = inputs.get(0);
        numberOfServers = inputs.get(1);
        timeLimit = inputs.get(2);
        minArrivalTime = inputs.get(3);
        maxArrivalTime = inputs.get(4);
        minProcessingTime = inputs.get(5);
        maxProcessingTime = inputs.get(6);
        scheduler = new Scheduler(numberOfServers, 0);
    }

    public class ButtonPressed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> inputList = inputs.getInput();
            getInputs(inputList);
            inputs.close();
        }
    }
}