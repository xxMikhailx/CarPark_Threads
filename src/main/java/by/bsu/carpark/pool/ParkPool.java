package by.bsu.carpark.pool;

import by.bsu.carpark.entity.ParkLot;
import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Михаил on 06.12.2015.
 */
public class ParkPool {

    private static Logger LOG = Logger.getLogger(ParkPool.class);
    private final static int POOL_SIZE_PARK0 = 5;
    private final static int POOL_SIZE_PARK1 = 6;
    private final static int POOL_SIZE_PARK2 = 7;
    private final Semaphore semaphore0 = new Semaphore(POOL_SIZE_PARK0, true);
    private final Semaphore semaphore1 = new Semaphore(POOL_SIZE_PARK1, true);
    private final Semaphore semaphore2 = new Semaphore(POOL_SIZE_PARK2, true);

    private final Queue<ParkLot> park0 = new ConcurrentLinkedQueue<>();
    private final Queue<ParkLot> park1 = new ConcurrentLinkedQueue<>();
    private final Queue<ParkLot> park2 = new ConcurrentLinkedQueue<>();

    public ParkPool(Queue<ParkLot> lotPark0, Queue<ParkLot> lotPark1, Queue<ParkLot> lotPark2){
        park0.addAll(lotPark0);
        park1.addAll(lotPark1);
        park2.addAll(lotPark2);
    }

    public ParkLot getLot(long maxWaitMillis) {
        LOG.debug("Car #" + Thread.currentThread().getId() + " tried to getLot");
        ParkLot toReturn = null;
        try {
            if (semaphore0.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                LOG.debug("Car #" + Thread.currentThread().getId() + " poll from queue0");
                ParkLot lot = park0.poll();
                toReturn = lot;
            }
            else if (semaphore1.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)){
                LOG.debug("Car #" + Thread.currentThread().getId() + " poll from queue1");
                ParkLot lot = park1.poll();
                toReturn = lot;
            }
            else if (semaphore2.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)){
                LOG.debug("Car #" + Thread.currentThread().getId() + " poll from queue2");
                ParkLot lot = park2.poll();
                toReturn = lot;
            }
        } catch (InterruptedException e) {
            LOG.error(e);
        }
        return toReturn;
    }

    public void returnLot(ParkLot lot){
        if (lot.getParkId() == 0) {
            park0.add(lot);
            semaphore0.release();
        }
        else if (lot.getParkId() == 1){
            park1.add(lot);
            semaphore1.release();
        }
        else if (lot.getParkId() == 2){
            park2.add(lot);
            semaphore2.release();
        }
    }
}
