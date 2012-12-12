package edu.buaa.park;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class MaxAvailableParkStrategy implements ParkStrategy {
    @Override
    public ParkPlaceExtInfo getAvailablePark(List<ParkPlaceExtInfo> parks) {
        int maxsizeIndex=0;
        for(int i=1;i< parks.size();i++){
            if(parks.get(i).getAvailableNum()> parks.get(maxsizeIndex).getAvailableNum())
                maxsizeIndex=i;
        }
        if(parks.get(maxsizeIndex).getAvailableNum()==0)
            throw new ParkFullException("所有的停车场都已满");
        return   parks.get(maxsizeIndex);
    }
}
