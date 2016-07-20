package data;

import info.Commodity;
import info.CommodityItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sora on 2016/7/19.
 */
public class ShopCart {

    private HashMap<String, CommodityItem> itemMap = new HashMap<>();

    public CommodityItem getItem(String barcode) {
        return itemMap.get(barcode);
    }

    public void add(Commodity commodity, int quantity) {
        String barcode = commodity.getBarcode();
        if (itemMap.containsKey(barcode)) {
            CommodityItem item = itemMap.get(barcode);
            item.add(quantity);
            itemMap.put(barcode, item);
        } else {
            CommodityItem item = new CommodityItem(commodity, quantity);
            itemMap.put(barcode, item);
        }
    }

    public List<CommodityItem> getItems() {
        return new ArrayList<>(itemMap.values());
    }
}
