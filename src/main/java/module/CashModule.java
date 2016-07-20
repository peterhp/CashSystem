package module;

import data.CommodityManager;
import data.PromotionManager;
import data.ShopCart;
import util.CartParser;
import util.CartReceiptCalculator;

import java.io.FileNotFoundException;

/**
 * Created by Sora on 2016/7/20.
 */
public class CashModule {

    public static String getPath(String fileName) {
        return CashModule.class.getClassLoader()
                .getResource(fileName).getPath();
    }

    public void init() {
        try {
            CommodityManager.getManager().readFromJsonFile(
                    getPath("commodity_list.json"));
            PromotionManager.getManager().readFromJsonFile(
                    getPath("promotion_list.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getReceiptText(ShopCart cart) {
        return CartReceiptCalculator
                .getReceipt(cart).getText();
    }

    public static void main(String[] args) {
        CashModule cashModule = new CashModule();
        cashModule.init();

        String jsonCart =
                "[" +
                    "\"ITEM000001\"," +
                    "\"ITEM000001\"," +
                    "\"ITEM000001\"," +
                    "\"ITEM000002\"," +
                    "\"ITEM000002\"," +
                    "\"ITEM000003-2\"," +
                    "\"ITEM000005\"," +
                    "\"ITEM000005\"," +
                    "\"ITEM000005\"," +
                    "\"ITEM000005\"," +
                    "\"ITEM000005\"," +
                    "\"ITEM000005\"" +
                "]";

        ShopCart cart = CartParser.readFromJsonString(jsonCart);

        String receiptText = cashModule.getReceiptText(cart);

        System.out.println(receiptText);
    }
}
