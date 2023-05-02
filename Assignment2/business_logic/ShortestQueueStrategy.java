package business_logic;

import java.util.List;

import model.Server;
import model.Task;

public class ShortestQueueStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        
        // initialize min with the length of the first queue
        Server shortest = servers.get(0);
        int min = shortest.getTasks().length;

        // iterate through the servers list
        for (Server server : servers) {
            // update min if neccessary
            if (server.getTasks().length < min) {
                shortest = server;
                min = shortest.getTasks().length;
            }
        }

        // the startegy has done its job and we can add the task
        shortest.addTask(t);

    }
    
}