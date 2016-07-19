package info;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sora on 2016/7/18.
 */
public abstract class Promotion {

    private Set<String> barcodes = new HashSet<>();

    public void add(String barcode) {
        barcodes.add(barcode);
    }

    public boolean contains(Commodity commodity) {
        return barcodes.contains(commodity.getBarcode());
    }

    public abstract float calcDiscount(Commodity commodity, int count);

    public static Promotion getPromotion(String type) {
        if (type.equals("BUY_THREE_GET_ONE_FREE")) {
            return new Buy3Free1Promotion();
        } else if (type.equals("FIVE_PERCENT_DISCOUNT")) {
            return new DiscountPromotion();
        } else {
            return null;
        }
    }

}
