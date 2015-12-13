package by.bsu.carpark.pool;

import by.bsu.carpark.entity.CarPark;
import by.bsu.carpark.entity.ParkLot;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Михаил on 06.12.2015.
 */
public class ParkPool {

    private final static Logger LOG = Logger.getLogger(ParkPool.class);

    private ReentrantLock lock = new ReentrantLock();
    private ArrayList<CarPark> pool = new ArrayList<>();



    public ParkPool(){
    }

    public void addToPool(CarPark park){
        pool.add(park);
    }

    public ParkLot getLot(long maxWaitMillis) {
        LOG.debug("Car #" + Thread.currentThread().getId() + " tried to getLot");
        ParkLot toReturn = null;
            for (CarPark park : pool) {
                ParkLot lot = park.takeLot(maxWaitMillis);
                if (lot != null){
                    toReturn = lot;
                    break;
                }
                else {
                    LOG.info("Car #" + Thread.currentThread().getId() + " -> timeout. Going to another parking.");
                }
            }
        return toReturn;
    }

    public boolean returnLot(ParkLot lot){
        boolean toReturn = false;
        if (lock.tryLock()) {
            for (CarPark park : pool) {
                if (park.getParkId() == lot.getParkId()) {
                    park.returnLot(lot);
                }
            }
            toReturn = true;
            lock.unlock();
        }
        return toReturn;
    }
}
