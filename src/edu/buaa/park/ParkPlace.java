package edu.buaa.park;
import java.util.*;

public class ParkPlace {
    private int capacity;
    private Map<Ticket,Car> placeCarMaps=new HashMap<Ticket,Car>() ;
    public ParkPlace(int capacity) {
        this.capacity=capacity;
    }
    public Ticket  parkCar(Car car){
        if(capacity<=0) throw new ParkFullException("车库满了");
        Ticket ticket=new Ticket();
        placeCarMaps.put(ticket,car);
        capacity--;
        return ticket;
    }
    public int getAvailableNum() {
        return capacity;
    }
    public Car fecthCar(Ticket ticket) {

        Car car=placeCarMaps.get(ticket);
        if(car!=null){
        placeCarMaps.remove(ticket);
        capacity++;}
        return car;

    }
}
