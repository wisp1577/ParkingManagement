package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy {
    protected List<ParkPlaceExtInfo> parkPlaces;
    private final ParkStrategy parkStrategy;

    public ParkBoy(List<ParkPlaceExtInfo> parkPlaces,ParkStrategy parkStrategy) {
        this.parkPlaces = parkPlaces;
        this.parkStrategy = parkStrategy;
    }

    public Ticket park(Car car) {
        return parkStrategy.getAvailablePark(parkPlaces).parkCar(car);
    }

    public Integer getAvailableNum() {
        int availableNum=0;
        for(ParkPlace parkPlace:parkPlaces){
            availableNum+=parkPlace.getAvailableNum();
        }
        return availableNum;
    }
    public Car fetch(Ticket ticket) {
        Car fetchedCar=null;
        for(ParkPlace parkPlace:parkPlaces){
            fetchedCar=parkPlace.fecthCar(ticket);
            if(fetchedCar!=null){return fetchedCar;}
        }
        throw new NoCarException("没有此车");
    }

}
