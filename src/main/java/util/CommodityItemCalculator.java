package util;

import data.PromotionManager;
import info.CommodityItem;
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

    public Promotion getItemPromotion() {
        return promotion;
    }

    private float calcTotal() {
        return item.getCommodity().getPrice()
                * item.getQuantity();
    }

    public float calcCost() {
        return calcTotal() - calcSave();
    }

    public float calcDiscount() {
        return promotion != null ?
                promotion.calcDiscount(item) : 0;
    }

    public float calcSave() {
        return promotion != null ?
                promotion.calcSaving(item) : 0;
    }
}
