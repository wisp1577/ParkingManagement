package edu.buaa.park;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo-lb
 * Date: 12-12-4
 * Time: 下午4:48
 * To change this template use File | Settings | File Templates.
 */
public class ParkPlaceExtInfo extends ParkPlace{
    //停车场编号
    private String parkPlaceNo="";
    //停车场名称
    private String parkPlaceName="";

    public ParkPlaceExtInfo(String parkPlaceNo,String parkPlaceName,int capacity) {
        super(capacity);
        this.parkPlaceName = parkPlaceName;
        this.parkPlaceNo = parkPlaceNo;
    }

    public String getParkPlaceNo() {
        return parkPlaceNo;
    }

    public void setParkPlaceNo(String parkPlaceNo) {
        this.parkPlaceNo = parkPlaceNo;
    }

    public String getParkPlaceName() {
        return parkPlaceName;
    }

    public void setParkPlaceName(String parkPlaceName) {
        this.parkPlaceName = parkPlaceName;
    }
}
