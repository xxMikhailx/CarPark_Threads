package by.bsu.carpark.entity;

import by.bsu.carpark.pool.ParkPool;
import org.apache.log4j.Logger;

/**
 * Created by Михаил on 06.12.2015.
 */
public class Car extends Thread {

    private static Logger LOG = Logger.getLogger(Car.class);

    private boolean standing;
    private ParkPool pool;

    public Car(ParkPool pool){
        this.pool = pool;
        LOG.debug("Car #" + this.getId() + " is created");
    }

    public void run(){
        this.setName("Car-" + this.getId());
        ParkLot lot;

        do {
            LOG.debug("Try of getting lot (while). Car #" + this.getId());
            lot = pool.getLot(500);
        } while (lot == null);
        System.out.println("Car №" + this.getId() + " took park lot №" + lot.getLotId() + ". Parking №" + lot.getParkId());
        standing = true;

        lot.using();

        standing = false;
        System.out.println("Car №" + this.getId() + " : " + lot.getLotId() + " lot released. Parking №" + lot.getParkId());
        pool.returnLot(lot);
        /*try {
                lot = pool.getLot(500);
            standing = true;
            System.out.println("Car №" + this.getId() + " took park lot №" + lot.getLotId());
//            LOG.info("Car №" + this.getId() + " start using lot №" + lot.getLotId());
            lot.using();
//            LOG.info("Car №" + this.getId() + " end using lot №" + lot.getLotId());
        } catch (ParkException e){
            System.out.println("Car №" + this.getId() + " lost -> " + e.getMessage());
        } finally {
            if (lot != null){
                standing = false;
                System.out.println("Car №" + this.getId() + " : " + lot.getLotId() + " lot released");
                pool.returnLot(lot);
            }
        }*/
        LOG.debug("Car #" + this.getId() + " is terminated");
    }

    public boolean isStanding(){
        return standing;
    }

}