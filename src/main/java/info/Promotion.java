package info;

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

}
