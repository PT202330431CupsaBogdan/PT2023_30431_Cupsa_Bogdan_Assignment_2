package business_logic;

import java.util.ArrayList;
import java.util.List;

import model.Server;
import model.Task;

public class Scheduler {
    
    private List<Server> servers;
    private int maxNoServers;
    private Strategy strategy = new TimeStrategy();

    public Scheduler(int maxNoServers, int maxTasksPerServer) {

        this.maxNoServers = maxNoServers;

        // initialize server list
        // we get rid of the null pointer exception given
        // by the add method
        servers = new ArrayList<>();

        // until we reach the maximum
        for (int i = 0; i < maxNoServers; i++) {

            // create server object
            Server server = new Server(i);

            // create thread object
            Thread thread = new Thread(server);

            // add server to the list
            servers.add(server);

            // start thread
            thread.start();
        }

    }

    public void changeStrategy(SelectionPolicy policy) {

        // apply strategy pattern to instantiate the strategy with the one corresponding to the policy 

        // shortest queue
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }

        // shortest time
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }

    }

    public void dispatchTask(Task t) {

        // dispatch task to one of the servers
        strategy.addTask(servers, t);

    }

    public List<Server> getServers() {
        return servers;
    }

    @Override
    public String toString() {
        String printed = "";

        for(Server server : servers) {
            printed += server.toString() + '\n';
        }
        
        printed += "\n";
        return printed;
    }
}