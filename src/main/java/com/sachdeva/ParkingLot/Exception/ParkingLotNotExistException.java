package com.sachdeva.ParkingLot.Exception;

public class ParkingLotNotExistException extends RuntimeException{
    public ParkingLotNotExistException(String message){
        super(message);
    }
}
