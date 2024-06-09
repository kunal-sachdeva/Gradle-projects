package com.sachdeva.ParkingLot.model;

import com.sachdeva.ParkingLot.Exception.EmptySlotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLot {
    private static final Logger LOG = LoggerFactory.getLogger(ParkingLot.class);
    private ConcurrentHashMap<Integer, Car> lotToVehicleMap;
    private ConcurrentSkipListSet<Integer> freeSlotSet;

    public ParkingLot(int initialSlots){
        LOG.info("Please wait. Initializing ParkingLot Service with slots = {}",initialSlots);
        lotToVehicleMap = new ConcurrentHashMap<>();
        freeSlotSet = IntStream
                .rangeClosed(1,initialSlots)
                .boxed()
                .collect(Collectors.toCollection(ConcurrentSkipListSet::new));
    }

    public void park(String regNo, String color){
        int freeSlot = getFreeSlot();
        Car car = new Car(regNo,color);
        LOG.info("Available Free Slot: {}, Parking the car: {}",freeSlot,car);
        lotToVehicleMap.put(freeSlot, car);
        freeSlotSet.pollFirst();
    }
    public void leave(int slot) {
        Car car = lotToVehicleMap.get(slot);
        if(Objects.isNull(car)){
            throw new EmptySlotException("Slot :"+slot+" is empty, No car available to leave ");
        }
        LOG.info("Car : {} leaving slot: {}",car,slot);
        lotToVehicleMap.remove(slot);
        freeSlotSet.add(slot);
    }
    public int getFreeSlot() throws EmptySlotException {
        if(freeSlotSet.isEmpty()) {
            throw new EmptySlotException("No free slot available as of now!");
        }
        return freeSlotSet.first();
    }

    public String status(){
        LOG.info("Generating ParkingLot status report..");
        StringBuilder status = new StringBuilder();
        status.append("Slot\tRegNo\tColor\n");
        lotToVehicleMap
                .entrySet()
                .forEach(entry -> status.append(entry.getKey())
                        .append("\t")
                        .append(entry.getValue().regNo())
                        .append("\t")
                        .append(entry.getValue().color())
                        .append("\n")
                );
        return status.toString();
    }
    public List<String> regNoForCarsWithColor(String color){
        LOG.info("Fetching RegNo of all the cars with color: {}",color);
        return lotToVehicleMap
                .entrySet()
                .stream()
                .filter(e-> color.equals(e.getValue().color()))
                .map(e -> e.getValue().color())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> slotNoForCarsWithColor(String color){{
        LOG.info("Fetching SlotNo of all the cars with color: {}",color);
        return lotToVehicleMap
                .entrySet()
                .stream()
                .filter(e-> color.equals(e.getValue().color()))
                .map(e -> e.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    }
}
