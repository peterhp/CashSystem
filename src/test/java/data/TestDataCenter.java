package data;

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
}
