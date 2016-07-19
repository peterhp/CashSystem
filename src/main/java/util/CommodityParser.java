package util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.Commodity;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Sora on 2016/7/19.
 */
public class CommodityParser {

    public static HashMap<String, Commodity>
            readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        HashMap<String, Commodity> commodityMap = new HashMap<>();

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

        return commodityMap;
    }
}
