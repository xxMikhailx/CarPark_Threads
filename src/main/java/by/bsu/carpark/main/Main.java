package by.bsu.carpark.main;

import by.bsu.carpark.entity.Car;
import by.bsu.carpark.entity.ParkLot;
import by.bsu.carpark.pool.ParkPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.LinkedList;

/**
 * Created by Михаил on 06.12.2015.
 */
public class Main {

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LinkedList<ParkLot> listPark0 = new LinkedList<ParkLot>() {
            {
                this.add(new ParkLot(1,0));
                this.add(new ParkLot(2,0));
                this.add(new ParkLot(3,0));
                this.add(new ParkLot(4,0));
                this.add(new ParkLot(5,0));
            }
        };
        LinkedList<ParkLot> listPark1 = new LinkedList<ParkLot>() {
            {
                this.add(new ParkLot(1,1));
                this.add(new ParkLot(2,1));
                this.add(new ParkLot(3,1));
                this.add(new ParkLot(4,1));
                this.add(new ParkLot(5,1));
                this.add(new ParkLot(6,1));
            }
        };
        LinkedList<ParkLot> listPark2 = new LinkedList<ParkLot>() {
            {
                this.add(new ParkLot(1,2));
                this.add(new ParkLot(2,2));
                this.add(new ParkLot(3,2));
                this.add(new ParkLot(4,2));
                this.add(new ParkLot(5,2));
                this.add(new ParkLot(6,2));
                this.add(new ParkLot(7,2));
            }
        };

        ParkPool pool = new ParkPool(listPark0, listPark1, listPark2);

        for (int i = 0; i < 200; i++) {
            new Car(pool).start();
            /*try {
                TimeUnit.MILLISECONDS.sleep(new java.util.Random().nextInt(20));
            } catch (InterruptedException e) {
                LOG.error(e);
            }*/
        }

    }

}
