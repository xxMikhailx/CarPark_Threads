package by.bsu.carpark.main;

import by.bsu.carpark.creator.Creator;
import by.bsu.carpark.entity.Car;
import by.bsu.carpark.pool.ParkPool;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


/**
 * Created by Михаил on 06.12.2015.
 */
public class Main {

    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private final static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ParkPool pool = new ParkPool();
        pool.addToPool(Creator.createPark0());
        pool.addToPool(Creator.createPark1());
        pool.addToPool(Creator.createPark2());

        for (int i = 0; i < 200; i++) {
            new Car(pool).start();
        }

    }

}
