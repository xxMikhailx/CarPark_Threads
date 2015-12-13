package by.bsu.carpark.creator;

import by.bsu.carpark.entity.CarPark;
import by.bsu.carpark.entity.ParkLot;

/**
 * Created by Михаил on 13.12.2015.
 */
public class Creator {

    public static CarPark createPark0(){
        CarPark park = new CarPark(0,5);

        park.addToParkLots(new ParkLot(1, park.getParkId()));
        park.addToParkLots(new ParkLot(2, park.getParkId()));
        park.addToParkLots(new ParkLot(3, park.getParkId()));
        park.addToParkLots(new ParkLot(4, park.getParkId()));
        park.addToParkLots(new ParkLot(5, park.getParkId()));

        return park;
    }

    public static CarPark createPark1(){
        CarPark park = new CarPark(1,6);

        park.addToParkLots(new ParkLot(1, park.getParkId()));
        park.addToParkLots(new ParkLot(2, park.getParkId()));
        park.addToParkLots(new ParkLot(3, park.getParkId()));
        park.addToParkLots(new ParkLot(4, park.getParkId()));
        park.addToParkLots(new ParkLot(5, park.getParkId()));
        park.addToParkLots(new ParkLot(6, park.getParkId()));

        return park;
    }

    public static CarPark createPark2(){
        CarPark park = new CarPark(2,7);

        park.addToParkLots(new ParkLot(1, park.getParkId()));
        park.addToParkLots(new ParkLot(2, park.getParkId()));
        park.addToParkLots(new ParkLot(3, park.getParkId()));
        park.addToParkLots(new ParkLot(4, park.getParkId()));
        park.addToParkLots(new ParkLot(5, park.getParkId()));
        park.addToParkLots(new ParkLot(6, park.getParkId()));
        park.addToParkLots(new ParkLot(7, park.getParkId()));

        return park;
    }

}
