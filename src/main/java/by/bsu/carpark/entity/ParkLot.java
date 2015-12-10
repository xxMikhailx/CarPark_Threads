package by.bsu.carpark.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Михаил on 06.12.2015.
 */
public class ParkLot {

    private final static Logger LOG = Logger.getLogger(ParkLot.class);

    private int lotId;
    private int parkId;

    public ParkLot(int lotId, int parkId){
        super();
        this.lotId = lotId;
        this.parkId = parkId;
    }

    public int getLotId() {
        return lotId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public void using(){
        try{
            TimeUnit.MILLISECONDS.sleep(new java.util.Random().nextInt(200));
        } catch (InterruptedException e){
            LOG.error(e);
        }
    }

    @Override
    public String toString(){
        return "LOT #" + lotId;
    }
}
