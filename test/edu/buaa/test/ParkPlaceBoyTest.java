package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class ParkPlaceBoyTest {

    @Test
    public void parkBoy_ShouldParkCar(){
        Car car=new Car();
        int maxParkingNum=20;
        ParkPlaceExtInfo parkPlace=new ParkPlaceExtInfo("1001","1号停车场",maxParkingNum);
        ArrayList<ParkPlaceExtInfo> parkPlaces=new ArrayList<ParkPlaceExtInfo>();
        parkPlaces.add(parkPlace) ;
        ParkBoy parkBoy= new ParkBoy(parkPlaces, new FirstAvailableParkStrategy());
        Ticket ticket=parkBoy.park(car);
        Assert.assertEquals(new Integer(maxParkingNum - 1), parkBoy.getAvailableNum());
    }
    /*取车---

    * */
    @Test
    public void parkBoy_ShouldfetchCar(){
        Car car=new Car();
        int maxParkingNum=20;
        ParkPlaceExtInfo parkPlace=new ParkPlaceExtInfo("1001","1号停车场",maxParkingNum);
        ArrayList<ParkPlaceExtInfo> parkPlaces=new ArrayList<ParkPlaceExtInfo>();
        parkPlaces.add(parkPlace) ;
        ParkBoy parkBoy= new ParkBoy(parkPlaces, new FirstAvailableParkStrategy());
        Ticket ticket=parkBoy.park(car);
        Assert.assertSame(car,parkBoy.fetch(ticket));


    }


}
