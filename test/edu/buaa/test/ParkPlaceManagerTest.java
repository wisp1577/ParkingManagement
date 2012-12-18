package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: asp
 * Date: 12-12-4
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
public class ParkPlaceManagerTest {
    private List<ParkBoyInfo> parkBoys = null;

    @Before
    public void init()
    {
        parkBoys = new ArrayList<ParkBoyInfo>();
    }

    private ParkBoy init(String parkNo,String parkName,int maxParkingNum)
    {
        ParkPlaceExtInfo parkPlace=
                new ParkPlaceExtInfo(parkNo,parkName,maxParkingNum);
        ArrayList<ParkPlaceExtInfo> parkPlaces=new ArrayList<ParkPlaceExtInfo>();
        parkPlaces.add(parkPlace) ;
        return new ParkBoy(parkPlaces, new FirstAvailableParkStrategy());
    }

    private Integer totalAmount;
    private ParkBoy initMany()
    {
        List<ParkPlaceExtInfo> parkPlaces=new ArrayList<ParkPlaceExtInfo>();
        String[][] parkPlaceStr= new String[][]{{"1001","","10"},{"1002","","20"}};
        totalAmount=0;
        for(String[] parkstr:parkPlaceStr){
            parkPlaces.add(new ParkPlaceExtInfo(parkstr[0],parkstr[1],Integer.parseInt(parkstr[2])));
            totalAmount+=Integer.parseInt(parkstr[2]);
        }
        return new ParkBoy(parkPlaces, new MaxAvailableParkStrategy());
    }

    /**
     * 多个停车场
     * 全空
     * 停车
     */
    @Test
    public void parkManager_manyParkPlace_ShouldParkCar()
    {
        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1",true);
        parkManager.addParkBoy(parkBoyInfo,initMany());
        parkBoys.add(parkBoyInfo);

        //停车
        ParkBoy parkBoy = parkManager.getParkBoy(parkBoys.get(0));
        parkBoy.park(new Car());
        System.out.println((totalAmount.intValue() - 1)+":"+parkBoy.getAvailableNum());
        Assert.assertEquals(new Integer(totalAmount - 1), parkBoy.getAvailableNum());

    }

    /*
   * 都空
   * 取车
   * */
    //@Test
    @Test(expected = edu.buaa.park.NoCarException.class)
    public void should_fetch_Sucess_when_park_is_empty(){
        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        parkManager.addParkBoy(parkBoyInfo,initMany());
        parkBoys.add(parkBoyInfo);

        ParkBoy parkBoy = parkManager.getParkBoy(parkBoys.get(0));

        parkBoy.fetch(new Ticket());
    }


    /*
   * 不全为空 ,取车
   * */
    @Test
    public void should_fetch_Sucess_when_park_is_notempty(){

        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        parkManager.addParkBoy(parkBoyInfo,initMany());
        parkBoys.add(parkBoyInfo);

        ParkBoy parkBoy = parkManager.getParkBoy(parkBoys.get(0));

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

        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        parkManager.addParkBoy(parkBoyInfo,initMany());
        parkBoys.add(parkBoyInfo);

        ParkBoy parkBoy = parkManager.getParkBoy(parkBoys.get(0));

        for(int i=0;i<totalAmount;i++){
            parkBoy.park(new Car());}
        parkBoy.park(new Car());
    }

    /**
     * 管理者本身管理停车场
     * 停车
     */
    @Test
    public void parkManager_include_self_sub_parkBoy_ShouldParkCar()
    {
        Integer[] integers = new Integer[]{10,20,30,40};
        int index=0;
        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        parkManager.addParkBoy(parkBoyInfo,init("1001", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("boy2");
        parkManager.addParkBoy(parkBoyInfo,init("1002", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("boy3");
        parkManager.addParkBoy(parkBoyInfo,init("1003", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("myself");
        parkManager.addParkBoy(parkBoyInfo,init("1004", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        index=0;
        //停车
        for(ParkBoyInfo parkBoyPerson:parkBoys)
        {
            Car car=new Car();
            ParkBoy parkBoy = parkManager.getParkBoy(parkBoyPerson);
            Ticket ticket = parkBoy.park(car);
            System.out.println((index+1)+"、"+(integers[index].intValue()-1)+":"+parkBoy.getAvailableNum());
            Assert.assertEquals(new Integer(integers[index++].intValue()-1),parkBoy.getAvailableNum());
        }
     }

    /**
     * 管理者本身管理停车场
     * 取车
     */
    @Test
    public void parkManager_include_self_sub_parkBoy_FetchParkCar()
    {
        Integer[] integers = new Integer[]{10,20,30,40};
        int index=0;
        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        parkManager.addParkBoy(parkBoyInfo,init("1001", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("boy2");
        parkManager.addParkBoy(parkBoyInfo,init("1002", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("boy3");
        parkManager.addParkBoy(parkBoyInfo,init("1003", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("myself");
        parkManager.addParkBoy(parkBoyInfo,init("1004", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        index=0;
        //取车
        for(ParkBoyInfo parkBoyPerson:parkBoys)
        {
            Car car=new Car();
            ParkBoy parkBoy = parkManager.getParkBoy(parkBoyPerson);
            Ticket ticket = parkBoy.park(car);
            //System.out.println((index+1)+"、"+(integers[index].intValue()-1)+":"+parkBoy.getAvailableNum());
            Assert.assertSame(car,parkBoy.fetch(ticket));
        }
    }
}
