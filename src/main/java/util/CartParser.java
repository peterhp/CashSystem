package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import data.CommodityManager;
import data.ShopCart;

import java.io.FileNotFoundException;

/**
 * Created by Sora on 2016/7/19.
 */
public class CartParser {

    private static ShopCart parseJsonCart(JsonArray jsonItemArray) {
        ShopCart cart = new ShopCart();

        CommodityManager manager =
                CommodityManager.getManager();

        for (int i = 0; i < jsonItemArray.size(); ++i) {
            String itemText = jsonItemArray.get(i).getAsString();

            String barcode = itemText;
            int count = 1;
            int pos = itemText.indexOf('-');
            if (pos > 0) {
                barcode = itemText.substring(0, pos);
                count = Integer.valueOf(itemText.substring(pos + 1));
            }

            if (manager.contains(barcode)) {
                cart.add(manager.get(barcode), count);
            }
        }

        return cart;
    }

    public static ShopCart readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        JsonArray jsonItemArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        return parseJsonCart(jsonItemArray);
    }

    public static ShopCart readFromJsonString(String json) {
        JsonArray jsonItemArray =
                JsonReader.getJsonFromString(json)
                .getAsJsonArray();

        return parseJsonCart(jsonItemArray);
    }
}
