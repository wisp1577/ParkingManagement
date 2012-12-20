package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class MaxAvailableParkingLotChooser implements ParkingLotChooser{
    public ParkPlace getAvailablePark(List<ParkPlace> parkPlaceList){
        int maxsizeIndex=0;
        for(int i=1;i< parkPlaceList.size();i++){
            if(parkPlaceList.get(i).getAvailableNum()> parkPlaceList.get(maxsizeIndex).getAvailableNum())
                maxsizeIndex=i;
        }
        if(parkPlaceList.get(maxsizeIndex).getAvailableNum()==0)throw new ParkFullException("所有的停车场都已满");
        return   parkPlaceList.get(maxsizeIndex);
    }
}
