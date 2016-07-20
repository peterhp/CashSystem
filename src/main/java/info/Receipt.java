package info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sora on 2016/7/20.
 */
public class Receipt {

    private List<String> itemTextList = new ArrayList<>();
    private HashMap<String, List<String>> itemPromotionMap = new HashMap<>();
    private String totalText;

    public void addItem(CommodityItem item, float cost, float discount) {
        itemTextList.add(itemText(item, cost, discount));
    }

    public void addItemPromotion(CommodityItem item, Promotion promotion) {
        if (!itemPromotionText(item, promotion).isEmpty()) {
            String title = promotion.getPromotionTitle();
            String itemPromotion = itemPromotionText(item, promotion);

            if (itemPromotionMap.containsKey(title)) {
                List<String> itemPromotionList = itemPromotionMap.get(title);
                itemPromotionList.add(itemPromotion);
                itemPromotionMap.put(title, itemPromotionList);
            } else {
                List<String> itemPromotionList = new ArrayList<>();
                itemPromotionList.add(itemPromotion);
                itemPromotionMap.put(title, itemPromotionList);
            }
        }
    }

    public void setTotal(float cost, float save) {
        totalText = costText(cost, save);
    }

    private String getHeader() {
        return "***<没钱赚商店>购物清单***\n";
    }

    private String getTail() {
        return "**********************\n";
    }

    private String getPartLine() {
        return "----------------------\n";
    }

    public String getItemsText() {
        String text = "";
        for (String item : itemTextList) {
            text += item;
        }
        return text;
    }

    public String getPromotionsText() {
        List<String> textList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : itemPromotionMap.entrySet()) {
            String promotionText = entry.getKey() + "\n";
            for (String itemPromotion : entry.getValue()) {
                promotionText += itemPromotion;
            }
            textList.add(promotionText);
        }

        String text = "";
        for (int i = 0; i < textList.size(); ++i) {
            if (i == 0) {
                text = textList.get(i);
            } else {
                text += getPartLine();
                text += textList.get(i);
            }
        }

        return text;
    }

    public String getTotalText() {
        return totalText;
    }

    public String getText() {
        String text = getHeader();

        text += getItemsText() + getPartLine();

        String str = getPromotionsText();
        if (!str.isEmpty()) {
            text += str + getPartLine();
        }

        text += getTotalText();

        text += getTail();

        return text;
    }

    public static String itemText(CommodityItem item, float cost, float discount) {
        Commodity commodity = item.getCommodity();
        String text = String.format("名称：%s，数量：%d%s，单价：%.2f（元），小计：%.2f（元）",
                commodity.getName(), item.getQuantity(), commodity.getUnit(),
                commodity.getPrice(), cost);
        if (discount > 0) {
            text += String.format("，节省%.2f（元）", discount);
        }
        return text + "\n";
    }

    public static String itemPromotionText(CommodityItem item, Promotion promotion) {
        if (!promotion.getPromotionTitle().isEmpty() &&
                !promotion.getItemPromotion(item).isEmpty()) {
            return promotion.getItemPromotion(item) + "\n";
        }
        return "";
    }

    public static String costText(float cost, float save) {
        String text = String.format("总计：%.2f（元）\n", cost);

        if (save > 0.0f) {
            text += String.format("节省：%.2f（元）\n", save);
        }

        return text;
    }
}
