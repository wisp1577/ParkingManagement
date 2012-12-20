package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-11-25
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoyTest_WhenHavingSeveralParkplace {
    private Integer totalAmount;
    private ParkingBoy parkingBoy;
    private  List<ParkPlace> parkPlaces;
    @Before
    public void init(){
        List<ParkPlace> parkPlaces=new ArrayList<ParkPlace>();
        Integer[] parkPlaceNums= new Integer[]{10, 20};
        totalAmount=0;
        for(Integer parknum:parkPlaceNums){
              parkPlaces.add(new ParkPlace(parknum));
            totalAmount+=parknum;
        }
        parkingBoy= new ParkingBoy(parkPlaces, new MaxAvailableParkingLotChooser());
        this.parkPlaces=parkPlaces;
    }

    /*
    * 都空       停车
    * */
    @Test
    public void should_park_Sucess_when_park_is_empty(){
        parkingBoy.park(new Car())  ;
        Assert.assertEquals(new Integer(totalAmount-1),parkingBoy.getAvailableNum());
    }
    /*
   * 都空              取车
   * */
    @Test(expected = edu.buaa.park.NoCarException.class)
    public void should_fetch_Sucess_when_park_is_empty(){
        parkingBoy.fetch(new Ticket());
    }

    /*
   * 不全为空 ,取车
   * */
    @Test
    public void should_fetch_Sucess_when_park_is_notempty(){
        for(int i=0;i<totalAmount/2;i++){
        parkingBoy.park(new Car());}
        Car car=new Car();
        Ticket ticket=parkingBoy.park(car);
        Assert.assertSame(car,parkingBoy.fetch(ticket));
    }

    /*
   * 全满 ,停车
   * */
    @Test(expected = edu.buaa.park.ParkFullException.class)
    public void should_throwParkFullException_if_park_when_park_is_full(){
        for(int i=0;i<totalAmount;i++){
            parkingBoy.park(new Car());}
        parkingBoy.park(new Car());
    }

    /*smartingboy测试
    *    停车停在空车位多的那个停车场
    * */
    @Test
    public  void should_park_in_the_more_empty_parkplace(){
        parkingBoy.park(new Car());
        Assert.assertEquals(19, parkPlaces.get(1).getAvailableNum());
    }
    /*smartingboy测试
    两个车库空间相同时停在第一个
    * */
    @Test
    public void  should_park_in_the_first_parkplace_if_park_availableSize_same(){
        for(int i=0;i<10;i++){
            parkingBoy.park(new Car());}
        parkingBoy.park(new Car());
        Assert.assertEquals(9, parkPlaces.get(0).getAvailableNum());
    }
 }
