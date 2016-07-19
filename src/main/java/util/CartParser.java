package util;

import com.google.gson.JsonArray;
import data.ShopCart;

import java.io.FileNotFoundException;

/**
 * Created by Sora on 2016/7/19.
 */
public class CartParser {

    public static ShopCart readFromJsonFile(String jsonFile)
            throws FileNotFoundException {
        ShopCart cart = new ShopCart();

        JsonArray jsonItemArray =
                JsonReader.getJsonFromFile(jsonFile)
                .getAsJsonArray();

        for (int i = 0; i < jsonItemArray.size(); ++i) {
            String itemText = jsonItemArray.get(i).getAsString();

            String barcode = itemText;
            int count = 1;

            int pos = itemText.indexOf('-');
            if (pos > 0) {
                barcode = itemText.substring(0, pos);
                count = Integer.valueOf(itemText.substring(pos + 1));
            }

            cart.add(barcode, count);
        }

        return cart;
    }
}
