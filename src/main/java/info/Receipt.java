package info;

import java.util.List;

/**
 * Created by Sora on 2016/7/20.
 */
public class Receipt {

    public static String itemText(CommodityItem item, float cost, float discount) {
        Commodity commodity = item.getCommodity();
        String text = String.format("名称：%s，数量：%d%s，单价：%.2f（元），小计：%.2f（元）",
                commodity.getName(), item.getQuantity(), commodity.getUnit(),
                commodity.getPrice(), cost);
        if (discount > 0) {
            text += String.format("，节省%.2f（元）", discount);
        }
        return text;
    }

    public static String promotionText(Promotion promotion, List<CommodityItem> itemList) {
        if (itemList.isEmpty() || promotion.getPromotionTitle().isEmpty()) {
            return "";
        }

        String text = promotion.getPromotionTitle() + "\n";
        for (CommodityItem item : itemList) {
            if (!promotion.getItemPromotion(item).isEmpty()) {
                text += promotion.getItemPromotion(item) + "\n";
            }
        }

        return text;
    }
}
