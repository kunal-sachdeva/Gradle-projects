package com.sachdeva.ParkingLot.Exception;

public class EmptySlotException extends RuntimeException{
    public EmptySlotException(String message){
        super(message);
    }
}
