package info;

/**
 * Created by Sora on 2016/7/19.
 */
public class DiscountPromotion extends Promotion {

    @Override
    public float calcDiscount(CommodityItem item) {
        return contains(item.getCommodity()) ?
                item.getCommodity().getPrice() * item.getQuantity() * 0.05f :
                0;
    }

    @Override
    public float calcSaving(CommodityItem item) {
        return calcDiscount(item);
    }

    @Override
    public String getPromotionTitle() {
        return "";
    }

    @Override
    public String getItemPromotion(CommodityItem item) {
        return "";
    }
}
