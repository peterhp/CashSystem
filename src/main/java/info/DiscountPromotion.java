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
}
