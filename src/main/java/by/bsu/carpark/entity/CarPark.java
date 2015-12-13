package by.bsu.carpark.entity;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Михаил on 10.12.2015.
 */
public class CarPark {

    private final static Logger LOG = Logger.getLogger(ParkLot.class);

    private int parkId;
    private int parkSize;
    private Queue<ParkLot> lots = new ConcurrentLinkedQueue<>();
    private Semaphore semaphore;

    public CarPark(int parkId, int parkSize){
        this.parkId = parkId;
        this.parkSize = parkSize;
        this.semaphore = new Semaphore(parkSize, true);
    }

    public int getParkId() {
        return parkId;
    }

    public Queue<ParkLot> getLots(){
        Queue<ParkLot> lotsClone = new LinkedList<>();
        lotsClone.addAll(lots);
        return lotsClone;
    }

    public void addToParkLots(ParkLot lot){
        if (parkSize >= 0){
            lots.add(lot);
            parkSize--;
        }
    }

    public ParkLot takeLot(long maxWaitMillis){
        ParkLot toReturn = null;
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                LOG.debug("Car #" + Thread.currentThread().getId() + " poll from park #" + CarPark.this.parkId);
                ParkLot lot = lots.poll();
                LOG.debug("Car #" + Thread.currentThread().getId() +  " poll " + lot);
                toReturn = lot;
            }
        } catch (InterruptedException e) {
            LOG.error(e);
        }
        return toReturn;
    }

    public void returnLot(ParkLot lot){
        lots.add(lot);
        semaphore.release();
    }
}
