package data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import info.Buy3Free1Promotion;
import info.Commodity;
import info.DiscountPromotion;
import info.Promotion;
import util.JsonReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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

    private HashMap<String, Promotion> promotionMap = new HashMap<>();
    private static Promotion buildPromotion(String type) {
        if (type.equals("BUY_THREE_GET_ONE_FREE")) {
            return new Buy3Free1Promotion();
        } else if (type.equals("FIVE_PERCENT_DISCOUNT")) {
            return new DiscountPromotion();
        } else {
            return null;
        }
    }

    public void readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        JsonArray jsonPromotionArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        for (int i = 0; i < jsonPromotionArray.size(); ++i) {
            JsonObject jsonPromotion = jsonPromotionArray
                    .get(i).getAsJsonObject();

            String type = jsonPromotion.get("type").getAsString();
            Promotion promotion = buildPromotion(type);

            if (promotion != null) {
                JsonArray jsonBarcodes = jsonPromotion
                        .get("barcodes").getAsJsonArray();
                for (int j = 0; j < jsonBarcodes.size(); ++j) {
                    promotion.add(jsonBarcodes.get(j).getAsString());
                }

                promotionMap.put(type, promotion);
            }
        }
    }

    public int count() {
        return promotionMap.size();
    }

    public Promotion getPromotion(Commodity commodity) {
        Promotion buy3free1Promotion =
                promotionMap.get("BUY_THREE_GET_ONE_FREE");
        Promotion discountPromotion =
                promotionMap.get("FIVE_PERCENT_DISCOUNT");

        if (buy3free1Promotion != null &&
                buy3free1Promotion.contains(commodity)) {
            return buy3free1Promotion;
        } else if (discountPromotion != null &&
                discountPromotion.contains(commodity)) {
            return discountPromotion;
        } else {
            return null;
        }
    }
}
