package edu.buaa.park;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 11-6-22
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class ParkFullException extends RuntimeException{
    public ParkFullException() {
    }
    public ParkFullException(String message) {
        super(message);
    }
}
