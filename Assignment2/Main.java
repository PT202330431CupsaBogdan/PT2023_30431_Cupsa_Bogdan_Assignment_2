import business_logic.SimulationManager;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        SimulationManager gen = new SimulationManager();

        // suspend until inputs given
        while (gen.getTimeLimit() == -1) {

            Thread.sleep(1000);

        }

        Thread t = new Thread(gen);
        t.start();

    }

}