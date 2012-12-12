package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
public class ParkPlaceBoyTest_WhenHavingSeveralParkplace {
    private Integer totalAmount;
    private ParkBoy parkBoy;
    private  List<ParkPlaceExtInfo> parkPlacesExtInfo;

    @Before
    public void init(){
        List<ParkPlaceExtInfo> parkPlacesExtInfo=new ArrayList<ParkPlaceExtInfo>();
        String[][] parkPlaceStr= new String[][]{{"1001","","10"},{"1002","","20"}};
        totalAmount=0;
        for(String[] parkstr:parkPlaceStr){
            parkPlacesExtInfo.add(new ParkPlaceExtInfo(parkstr[0],parkstr[1],Integer.parseInt(parkstr[2])));
            totalAmount+=Integer.parseInt(parkstr[2]);
        }
        parkBoy= new ParkBoy(parkPlacesExtInfo, new MaxAvailableParkStrategy());
        this.parkPlacesExtInfo=parkPlacesExtInfo;
    }

    /*
   * 都空
   * 停车
   * */
    @Test
    public void should_park_Sucess_when_park_is_empty(){
        parkBoy.park(new Car())  ;
        Assert.assertEquals(new Integer(totalAmount - 1), parkBoy.getAvailableNum());
    }

    /*
   * 都空
   * 取车
   * */
    @Test(expected = edu.buaa.park.NoCarException.class)
    public void should_fetch_Sucess_when_park_is_empty(){
        parkBoy.fetch(new Ticket());
    }

    /*
   * 不全为空 ,取车
   * */
    @Test
    public void should_fetch_Sucess_when_park_is_notempty(){
        for(int i=0;i<totalAmount/2;i++){
            parkBoy.park(new Car());}
        Car car=new Car();
        Ticket ticket=parkBoy.park(car);
        Assert.assertSame(car,parkBoy.fetch(ticket));
    }

    /*
   * 全满 ,停车
   * */
    @Test(expected = edu.buaa.park.ParkFullException.class)
    public void should_throwParkFullException_if_park_when_park_is_full(){
        for(int i=0;i<totalAmount;i++){
            parkBoy.park(new Car());}
        parkBoy.park(new Car());
    }

    /*smartingboy测试
    *    停车停在空车位多的那个停车场
    * */
    @Test
    public  void should_park_in_the_more_empty_parkplace(){
        parkBoy.park(new Car());
        Assert.assertEquals(19, parkPlacesExtInfo.get(1).getAvailableNum());
    }
    /*smartingboy测试
    两个车库空间相同时停在第一个
    * */
    @Test
    public void  should_park_in_the_first_parkplace_if_park_availableSize_same(){
        for(int i=0;i<10;i++){
            parkBoy.park(new Car());}
        parkBoy.park(new Car());
        Assert.assertEquals(9, parkPlacesExtInfo.get(0).getAvailableNum());
    }


}
