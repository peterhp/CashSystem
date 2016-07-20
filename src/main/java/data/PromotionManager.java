package data;

import info.Buy3Free1Promotion;
import info.Commodity;
import info.DiscountPromotion;
import info.Promotion;
import util.PromotionParser;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Sora on 2016/7/18.
 */
public class PromotionManager {

    private static PromotionManager manager =
            new PromotionManager();

    public static PromotionManager getManager() {
        return manager;
    }

    public static Promotion buildPromotion(String type) {
        if (type.equals("BUY_THREE_GET_ONE_FREE")) {
            return new Buy3Free1Promotion();
        } else if (type.equals("FIVE_PERCENT_DISCOUNT")) {
            return new DiscountPromotion();
        } else {
            return null;
        }
    }

    private HashMap<String, Promotion> promotionMap = null;

    public void readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        promotionMap = PromotionParser.readFromJsonFile(jsonFile);
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
