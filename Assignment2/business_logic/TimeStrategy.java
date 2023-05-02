package business_logic;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import model.Server;
import model.Task;

public class TimeStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
       
        // initialize min with the waiting period of the first Server
        // note that I consider the Server as one cashier
        // in front of the queue
        Server shortest = servers.get(0);
        int min = shortest.getWaitingPeriod().intValue();
        
        // iterate through servers list
        for(Server server : servers) {
            // update min if it is the case
            if (server.getWaitingPeriod().intValue() < min) {
                shortest = server;
                min = shortest.getWaitingPeriod().intValue();
            }
        }
        // the strategy has done its job and the shortest task is added
        shortest.addTask(t);
    }
    
}