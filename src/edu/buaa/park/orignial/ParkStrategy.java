package edu.buaa.park.orignial;

import edu.buaa.park.ParkPlace;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-2
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */
public interface ParkStrategy {
    ParkPlace getAvailablePark(Collection<ParkPlace> parks);
}
