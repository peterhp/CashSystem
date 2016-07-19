package data;

import java.util.HashMap;

/**
 * Created by Sora on 2016/7/19.
 */
public class ShopCart {

    private HashMap<String, Integer> itemMap = new HashMap<>();

    public int get(String barcode) {
        return itemMap.get(barcode);
    }

    public void add(String barcode, int count) {
        if (itemMap.containsKey(barcode)) {
            itemMap.put(barcode,
                    itemMap.get(barcode) + count);
        } else {
            itemMap.put(barcode, count);
        }
    }
}
