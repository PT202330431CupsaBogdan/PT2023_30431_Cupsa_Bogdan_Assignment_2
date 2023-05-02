package business_logic;

import java.util.List;

import model.Server;
import model.Task;

public interface Strategy {

    public void addTask(List<Server> servers, Task t);
    
}