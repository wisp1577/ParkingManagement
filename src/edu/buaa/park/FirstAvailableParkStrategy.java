package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午5:03
 * To change this template use File | Settings | File Templates.
 */
public class FirstAvailableParkStrategy implements ParkStrategy{

    @Override
    public ParkPlaceExtInfo getAvailablePark(List<ParkPlaceExtInfo> parks) {
        for(ParkPlaceExtInfo parkPlace:parks){
            if(parkPlace.getAvailableNum()>0)
                return parkPlace;
        }
        throw new ParkFullException("所有的停车场都已满");
    }
}
