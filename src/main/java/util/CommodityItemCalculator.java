package util;

import data.PromotionManager;
import info.CommodityItem;
import info.DiscountPromotion;
import info.Promotion;

/**
 * Created by Sora on 2016/7/20.
 */
public class CommodityItemCalculator {

    private CommodityItem item;
    private Promotion promotion;

    public CommodityItemCalculator(CommodityItem item) {
        this.item = item;
        this.promotion = PromotionManager.getManager()
                .getPromotion(item.getCommodity());
    }

    private float calcTotal() {
        return item.getCommodity().getPrice()
                * item.getQuantity();
    }

    public float calcCost() {
        return calcTotal() - calcSave();
    }

    public float calcDiscount() {
        if (promotion != null &&
                promotion instanceof DiscountPromotion) {
            return promotion.calcDiscount(item);
        }
        return 0;
    }

    public float calcSave() {
        return promotion != null ?
                promotion.calcDiscount(item) :
                0;
    }
}
