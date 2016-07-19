package info;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sora on 2016/7/18.
 */
public class Promotion {

    private String type;
    private Set<String> barcodes = new HashSet<>();

    public Promotion(String type) {
        this.type = type;
    }

    public void add(String barcode) {
        barcodes.add(barcode);
    }

    public boolean contains(Commodity commodity) {
        return barcodes.contains(commodity.getBarcode());
    }

    public float calcDiscount(Commodity commodity, int count) {
        if (contains(commodity)) {
            if (type.equals("BUY_THREE_GET_ONE_FREE")) {
                return commodity.getPrice() * (count / 3);
            } else if (type.equals("FIVE_PERCENT_DISCOUNT")) {
                return commodity.getPrice() * count * 0.05f;
            }
        }
        return 0;
    }
}
