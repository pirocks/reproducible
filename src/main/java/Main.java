import net.openhft.chronicle.hash.serialization.impl.ConstantSizeMarshaller;
import net.openhft.chronicle.map.ChronicleMap;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.IOException;

/**
 * Created by francis on 4/5/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        ChronicleMap<Integer, Thing> map = ChronicleMap
                .of(Integer.class, Thing.class)
                .name("the-historical-orderbook")
                .entries(100_000).valueSizeMarshaller(new ConstantSizeMarshaller(5000L))
                .checksumEntries(true)
                .createOrRecoverPersistedTo(new File("historical-orders.bin"));
        //add some stuff to ChronicleMap
        for (int i = 0; i < 1000; i++) {
            map.put(i,new Thing());
        }
        map.close();//close the map
        map = null;
        System.gc();//the map should now be completely gone
        map = ChronicleMap
                .of(Integer.class, Thing.class)
                .name("the-historical-orderbook")
                .entries(100_000).valueSizeMarshaller(new ConstantSizeMarshaller(5000L))
                .checksumEntries(true)
                .createOrRecoverPersistedTo(new File("historical-orders.bin"));//spits out errors here
    }
}
