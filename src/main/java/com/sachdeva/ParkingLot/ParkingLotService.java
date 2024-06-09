package com.sachdeva.ParkingLot;

import com.sachdeva.ParkingLot.Exception.ParkingLotNotExistException;
import com.sachdeva.ParkingLot.model.ParkingLot;

import java.util.List;
import java.util.Objects;

public class ParkingLotService {
    private ParkingLot parkingLot;

    public void createParkingLot(int size){
        parkingLot=new ParkingLot(size);
    }
    public boolean isParkingLotExist(){
        return !Objects.isNull(parkingLot);
    }
    public String getStatus(){
        if(!isParkingLotExist())
            throw new ParkingLotNotExistException("No parking lot");
        return parkingLot.status();
    }

    public List<String> getRegNo(String color){
        if(!isParkingLotExist())
            throw new ParkingLotNotExistException("No parking lot");
        return parkingLot.regNoForCarsWithColor(color);
    }

    public List<Integer> getSlotNo(String color){
        if(!isParkingLotExist())
            throw new ParkingLotNotExistException("No parking lot");
        return parkingLot.slotNoForCarsWithColor(color);
    }
}
