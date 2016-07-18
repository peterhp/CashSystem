package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.Commodity;

import java.io.*;
import java.nio.charset.Charset;
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
        InputStream in = new FileInputStream(jsonFile);
        Reader reader = new InputStreamReader(
                in, Charset.forName("UTF-8"));

        JsonArray jsonCommodityArray = new JsonParser()
                .parse(reader).getAsJsonArray();

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

    public Commodity get(String barcode) {
        return commodityMap.get(barcode);
    }
}
