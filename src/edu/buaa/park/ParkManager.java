package edu.buaa.park;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: asp
 * Date: 12-12-5
 * Time: 下午6:13
 * To change this template use File | Settings | File Templates.
 */
public class ParkManager {

    private Map<ParkBoyInfo,ParkBoy> parkBoyMap = new HashMap<ParkBoyInfo, ParkBoy>();

    public void addParkBoy(ParkBoyInfo parkBoyInfo,ParkBoy parkBoy)
    {
        this.parkBoyMap.put(parkBoyInfo,parkBoy);
    }
    public ParkBoy getParkBoy(ParkBoyInfo parkBoyInfo)
    {
         return parkBoyMap.get(parkBoyInfo);
    }

    public Map<ParkBoyInfo, ParkBoy> getParkBoyMap() {
        return parkBoyMap;
    }

    private Integer allManageTotalCapacity = 0,allManageAvailableNum = 0;
    /**
     * 显示主管报表
     */
    public void showParkManagerReporter()
    {
        allManageTotalCapacity = 0;
        allManageAvailableNum = 0;
        if(parkBoyMap.isEmpty())
          println(0,"没有停车仔，也没有停车场。");
        else
        {
            println(0,"停车场主管：");
            ParkBoyInfo[] parkBoyInfos = getParkBoyInfos();

            ParkBoy parkBoy = null;
            List<ParkPlaceExtInfo> parkPlaceExtInfos = null;

            for(int i = 0,m = parkBoyInfos.length;i<m;i++)
            {
                parkBoy = this.getParkBoy(parkBoyInfos[i]);
                if(parkBoyInfos[i].isManager())
                {
             //显示主管自管理停车场
            if(parkBoy != null)
            {
                parkPlaceExtInfos = parkBoy.getParkPlaces();

                if(parkPlaceExtInfos != null && !parkPlaceExtInfos.isEmpty())
                {
                    for(ParkPlaceExtInfo parkPlaceExtInfo:parkPlaceExtInfos)
                    {
                        allManageAvailableNum += parkPlaceExtInfo.getAvailableNum();
                        allManageTotalCapacity += parkPlaceExtInfo.getTotalCapacity();
                        println(0,"停车场编号："+parkPlaceExtInfo.getParkPlaceNo());
                        println(6,"车位数："+parkPlaceExtInfo.getTotalCapacity());
                        println(6,"空位数："+parkPlaceExtInfo.getAvailableNum());
                    }
                }
              }

            } //end isManage
            else
            {
                showParkingBoyReporter(parkBoyInfos[i]);
            }
        } //end for

            println(0,"Total车位数："+allManageTotalCapacity);
            println(0,"Total空位数："+allManageAvailableNum);
      }
    }

    private ParkBoyInfo[] getParkBoyInfos()
    {
        ParkBoyInfo temp = null;
        ParkBoyInfo[] parkBoyInfoArray = parkBoyMap.keySet().toArray(new ParkBoyInfo[parkBoyMap.size()]);
        int index = 0;
        for(int i =0,m = parkBoyInfoArray.length;i<m;i++)
        {
            //System.out.println("====="+parkBoyInfoArray[i].getParkBoyName()+"::"+parkBoyInfoArray[i].isManager());
            if(parkBoyInfoArray[i].isManager())
            {
                if(i>index)
                {
                  temp = parkBoyInfoArray[index];
                  parkBoyInfoArray[index]=parkBoyInfoArray[i];
                  parkBoyInfoArray[i] = temp;
                }
                index++;
            }
        }

        return parkBoyInfoArray;
    }


    /**
     * 显示停车仔报表
     */
    public void showParkingBoyReporter(ParkBoyInfo parkBoyInfo)
    {
        Integer allTotalCapacity = 0,allAvailableNum = 0;
        ParkBoy parkBoy = this.getParkBoy(parkBoyInfo);
        List<ParkPlaceExtInfo> parkPlaceExtInfos = parkBoy.getParkPlaces();
        println(0,"停车仔"+parkBoyInfo.getParkBoyName()+"：");
        if(parkPlaceExtInfos != null && !parkPlaceExtInfos.isEmpty())
        {
            for(ParkPlaceExtInfo parkPlaceExtInfo:parkPlaceExtInfos)
            {
                allAvailableNum += parkPlaceExtInfo.getAvailableNum();
                allTotalCapacity += parkPlaceExtInfo.getTotalCapacity();
                println(6,"停车场编号："+parkPlaceExtInfo.getParkPlaceNo());
                println(12,"车位数："+parkPlaceExtInfo.getTotalCapacity());
                println(12,"空位数："+parkPlaceExtInfo.getAvailableNum());
            }
            println(6,"所属停车场车位数："+allTotalCapacity.toString());
            println(6,"所属停车场空位数："+allAvailableNum.toString());
        }
        allManageTotalCapacity += allTotalCapacity;
        allManageAvailableNum += allAvailableNum;

    }

    private void println(int spaceNum,String content)
    {
        for(int i = 0;i<spaceNum;i++)
        System.out.print(" ");
        System.out.println(content);
    }

}
