package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data.PromotionManager;
import info.Promotion;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Sora on 2016/7/19.
 */
public class PromotionParser {

    public static HashMap<String, Promotion>
            readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        HashMap<String, Promotion> promotionMap = new HashMap<>();

        JsonArray jsonPromotionArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        for (int i = 0; i < jsonPromotionArray.size(); ++i) {
            JsonObject jsonPromotion = jsonPromotionArray
                    .get(i).getAsJsonObject();

            String type = jsonPromotion.get("type").getAsString();
            Promotion promotion = PromotionManager.buildPromotion(type);

            if (promotion != null) {
                JsonArray jsonBarcodes = jsonPromotion
                        .get("barcodes").getAsJsonArray();
                for (int j = 0; j < jsonBarcodes.size(); ++j) {
                    promotion.add(jsonBarcodes.get(j).getAsString());
                }

                promotionMap.put(type, promotion);
            }
        }

        return promotionMap;
    }
}
