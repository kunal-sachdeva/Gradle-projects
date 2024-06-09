package com.sachdeva.ParkingLot;

import org.apache.commons.cli.*;

import java.util.Scanner;

public class ParkingLotCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static ParkingLotService parkingLotService;

    public static void main(String[] args){
        System.out.println("Welcome to the Parking Application!");
        System.out.println("Type 'exit' to exit the application.");
        Options options = new Options();
        options.addOption("s", "create_parking_lot", true, "Start parking service with specified number of lots");
        options.addOption("p", "park", true, "Park a car with registration number and color");
        options.addOption("l", "leave", true, "Car leaving the parking lot with specified slot number");
        options.addOption("t", "status", false, "Display status of all parking slots");
        options.addOption("d", "registration_numbers_for_cars_with_colour", true, "Fetch list of regNo of cars with color");
        options.addOption("d", "slot_numbers_for_cars_with_colour", true, "Fetch list of slots of cars with color");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            //switch(cmd.toString())
            if (cmd.hasOption("s")) {
                int numLots = Integer.parseInt(cmd.getOptionValue("s"));
                //startParkingService(numLots);
            }

            if (cmd.hasOption("p")) {
                String[] parkArgs = cmd.getOptionValues("p");
                if (parkArgs.length == 2) {
                    String regNo = parkArgs[0];
                    String color = parkArgs[1];
                    int slotNumber = parkCar(regNo, color);
                    System.out.println("Car parked at slot " + slotNumber);
                } else {
                    System.err.println("Invalid arguments for park command. Expected: -p <regNo> <color>");
                }
            }

            if (cmd.hasOption("l")) {
                int slotNumber = Integer.parseInt(cmd.getOptionValue("l"));
                leaveParking(slotNumber);
            }

            if (cmd.hasOption("t")) {
                displayStatus();
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
