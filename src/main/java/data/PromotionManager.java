package data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import info.Promotion;

import java.io.*;
import java.nio.charset.Charset;
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
        InputStream in = new FileInputStream(jsonFile);
        Reader reader = new InputStreamReader(
                in, Charset.forName("UTF-8"));

        JsonArray jsonPromotionArray = new JsonParser()
                .parse(reader).getAsJsonArray();

        for (int i = 0; i < jsonPromotionArray.size(); ++i) {
            JsonObject jsonPromotion = jsonPromotionArray
                    .get(i).getAsJsonObject();

            Promotion promotion = new Promotion(
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
