package info;

/**
 * Created by Sora on 2016/7/19.
 */
public class DiscountPromotion extends Promotion {

    @Override
    public float calcDiscount(Commodity commodity, int count) {
        return contains(commodity) ? commodity.getPrice() * count * 0.05f : 0;
    }
}
