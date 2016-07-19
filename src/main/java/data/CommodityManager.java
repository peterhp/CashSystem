package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.Commodity;
import util.CommodityParser;
import util.JsonReader;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Sora on 2016/7/18.
 */
public class CommodityManager {

    private static CommodityManager manager =
            new CommodityManager();

    public static CommodityManager getManager() {
        return manager;
    }

    private HashMap<String, Commodity> commodityMap = null;

    public void readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        commodityMap = CommodityParser.readFromJsonFile(jsonFile);
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
