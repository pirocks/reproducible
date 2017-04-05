
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by francis on 4/5/2017.
 */
public class Thing implements Serializable{
    BigDecimal foo;
    BigDecimal bar;

    public Thing() {
        this.foo = BigDecimal.valueOf(Math.random());
        this.bar = BigDecimal.valueOf(Math.random());
    }
}
