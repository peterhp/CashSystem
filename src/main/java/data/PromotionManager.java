package data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.Promotion;
import util.JsonReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sora on 2016/7/18.
 */
public class PromotionManager {

    private static PromotionManager manager = null;

    public static PromotionManager getManager() {
        if (manager == null) {
            manager = new PromotionManager();
        }
        return manager;
    }

    private List<Promotion> promotionList = new ArrayList<>();

    public void readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        JsonArray jsonPromotionArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        for (int i = 0; i < jsonPromotionArray.size(); ++i) {
            JsonObject jsonPromotion = jsonPromotionArray
                    .get(i).getAsJsonObject();

            Promotion promotion = Promotion.getPromotion(
                    jsonPromotion.get("type").getAsString());

            JsonArray jsonBarcodes = jsonPromotion
                    .get("barcodes").getAsJsonArray();
            for (int j = 0; j < jsonBarcodes.size(); ++j) {
                promotion.add(jsonBarcodes.get(j).getAsString());
            }

            promotionList.add(promotion);
        }
    }

    public int count() {
        return promotionList.size();
    }
}
