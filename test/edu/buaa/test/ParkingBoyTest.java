package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoyTest {

    @Test
    public void parkBoy_ShouldParkCar(){
        Car car=new Car();
        int maxParkingNum=20;
        ParkPlace parkPlace=new ParkPlace(maxParkingNum);
        ArrayList<ParkPlace> parkPlaces=new ArrayList<ParkPlace>();
        parkPlaces.add(parkPlace) ;
        ParkingBoy parkingBoy= new ParkingBoy(parkPlaces, new FirstAvailableParkingLotChooser());
        Ticket ticket=parkingBoy.park(car);
        Assert.assertEquals(new Integer(maxParkingNum-1),parkingBoy.getAvailableNum());
    }
    /*取车---

    * */
    @Test
    public void parkBoy_ShouldfetchCar(){
        Car car=new Car();
        int maxParkingNum=20;
        ParkPlace parkPlace=new ParkPlace(maxParkingNum);
        ArrayList<ParkPlace> parkPlaces=new ArrayList<ParkPlace>();
        parkPlaces.add(parkPlace) ;
        ParkingBoy parkingBoy= new ParkingBoy(parkPlaces, new FirstAvailableParkingLotChooser());
        Ticket ticket=parkingBoy.park(car);
        Assert.assertSame(car,parkingBoy.fetch(ticket));


    }
}
