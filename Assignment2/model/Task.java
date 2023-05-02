package model;

public class Task {

    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int iD, int arrivalTime, int serviceTime) {
        ID = iD;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" + ID + ", " + arrivalTime + ", " + serviceTime + ")";
    }
}