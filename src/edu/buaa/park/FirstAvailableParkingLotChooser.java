package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class FirstAvailableParkingLotChooser implements ParkingLotChooser {
    @Override
    public ParkPlace getAvailablePark(List<ParkPlace> parkPlaceList){
        for(ParkPlace parkPlace:parkPlaceList){
            if(parkPlace.getAvailableNum()>0) return parkPlace;
        }
        throw new ParkFullException("所有的停车场都已满");
    }
}
