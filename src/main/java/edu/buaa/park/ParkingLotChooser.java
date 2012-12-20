package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-2
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public interface ParkingLotChooser {
    ParkPlace getAvailablePark(List<ParkPlace> parkPlaceList);
}
