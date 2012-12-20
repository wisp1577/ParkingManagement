package edu.buaa.park;

/**
 * Created with IntelliJ IDEA.
 * User: wangxuefeng
 * Date: 12-12-5
 * Time: 下午6:01
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoyInfo {
    private String parkBoyName = null;
    private boolean isManager = false;
    public ParkBoyInfo(String parkBoyName)
    {
        this.parkBoyName = parkBoyName;
    }
    public ParkBoyInfo(String parkBoyName,boolean isManager)
    {
        this.isManager = isManager;
        this.parkBoyName = parkBoyName;
    }

    public String getParkBoyName() {
        return parkBoyName;
    }

    public boolean isManager() {
        return isManager;
    }
}
