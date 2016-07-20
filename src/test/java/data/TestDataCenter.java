package data;

import info.Commodity;

import java.io.FileNotFoundException;

/**
 * Created by Sora on 2016/7/20.
 */
public class TestDataCenter {

    public static void initDataManager() {
        try {
            CommodityManager.getManager().readFromJsonFile(
                    ResFile.getPath("commodity_list.json"));
            PromotionManager.getManager().readFromJsonFile(
                    ResFile.getPath("promotion_list.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Commodity createCommodity() {
        Commodity commodity = new Commodity();
        commodity.setBarcode("ITEM000001");
        commodity.setName("可口可乐");
        commodity.setCategory("食品");
        commodity.setSubCategory("饮料");
        commodity.setUnit("瓶");
        commodity.setPrice(3.00f);
        return commodity;
    }
}
