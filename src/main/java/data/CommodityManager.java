package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.Commodity;
import util.JsonReader;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Sora on 2016/7/18.
 */
public class CommodityManager {

    private static CommodityManager manager = null;

    public static CommodityManager getManager() {
        if (manager == null) {
            manager = new CommodityManager();
        }
        return manager;
    }

    private HashMap<String, Commodity> commodityMap = new HashMap<>();

    public void readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        JsonArray jsonCommodityArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        for (int i = 0; i < jsonCommodityArray.size(); ++i) {
            JsonObject jsonCommodity = jsonCommodityArray
                    .get(i).getAsJsonObject();

            Commodity commodity = new Gson()
                    .fromJson(jsonCommodity, Commodity.class);
            commodityMap.put(commodity.getBarcode(), commodity);
        }
    }

    public int count() {
        return commodityMap.size();
    }

    public boolean contains(String barcode) {
        return commodityMap.containsKey(barcode);
    }

    public Commodity get(String barcode) {
        return commodityMap.get(barcode);
    }
}
