package edu.buaa.test;

import edu.buaa.park.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: asp
 * Date: 12-12-9
 * Time: 下午7:07
 * To change this template use File | Settings | File Templates.
 */
public class ParkReportTest {

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
        String[][] parkPlaceStr= new String[][]{{"P0001","","10"},{"P0002","","20"}};
        totalAmount=0;
        for(String[] parkstr:parkPlaceStr){
            parkPlaces.add(new ParkPlaceExtInfo(parkstr[0],parkstr[1],Integer.parseInt(parkstr[2])));
            totalAmount+=Integer.parseInt(parkstr[2]);
        }
        return new ParkBoy(parkPlaces, new MaxAvailableParkStrategy());
    }

   @Test
   public void parkingManager_show_reporter()
   {
       Integer[] integers = new Integer[]{10,30,30,40};
       int index=0;
       ParkManager parkManager = new ParkManager();
       ParkBoyInfo parkBoyInfo=new ParkBoyInfo("ParkBoy1",true);
       parkManager.addParkBoy(parkBoyInfo,init("1001", "", integers[index++].intValue()));
       parkBoys.add(parkBoyInfo);

       parkBoyInfo=new ParkBoyInfo("ParkBoy2",false);
       parkManager.addParkBoy(parkBoyInfo,initMany());
       parkBoys.add(parkBoyInfo);

       parkBoyInfo=new ParkBoyInfo("ParkBoy3");
       parkManager.addParkBoy(parkBoyInfo,init("1003", "", integers[index++].intValue()));
       parkBoys.add(parkBoyInfo);

       index=0;
       //停车
       for(ParkBoyInfo parkBoyPerson:parkBoys)
       {
           Car car=new Car();
           ParkBoy parkBoy = parkManager.getParkBoy(parkBoyPerson);
           Ticket ticket = parkBoy.park(car);
           // System.out.println((index+1)+"、"+(integers[index].intValue()-1)+":"+parkBoy.getAvailableNum());
           Assert.assertEquals(new Integer(integers[index++].intValue() - 1), parkBoy.getAvailableNum());
       }

       parkManager.showParkManagerReporter();

   }

    @Test
    public void parkingboy_show_reporter()
    {
        Integer[] integers = new Integer[]{30,20,10,40};
        int index=1;
        ParkManager parkManager = new ParkManager();
        ParkBoyInfo parkBoyInfo=new ParkBoyInfo("boy1");
        //parkManager.addParkBoy(parkBoyInfo,init("1001", "", integers[index++].intValue()));
        parkManager.addParkBoy(parkBoyInfo,initMany());
        parkBoys.add(parkBoyInfo);

        parkBoyInfo=new ParkBoyInfo("boy2");
        parkManager.addParkBoy(parkBoyInfo,init("1002", "", integers[index++].intValue()));
        parkBoys.add(parkBoyInfo);

        index=0;
        //停车
        for(ParkBoyInfo parkBoyPerson:parkBoys)
        {
            Car car=new Car();
            ParkBoy parkBoy = parkManager.getParkBoy(parkBoyPerson);
            Ticket ticket = parkBoy.park(car);
           // System.out.println((index+1)+"、"+(integers[index].intValue()-1)+":"+parkBoy.getAvailableNum());
            Assert.assertEquals(new Integer(integers[index++].intValue() - 1), parkBoy.getAvailableNum());
        }

        //显示报表
        for(ParkBoyInfo parkBoyPerson:parkBoys)
        {
           parkManager.showParkingBoyReporter(parkBoyPerson);
        }

    }


}
