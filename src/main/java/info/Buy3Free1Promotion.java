package info;

/**
 * Created by Sora on 2016/7/19.
 */
public class Buy3Free1Promotion extends Promotion {

    @Override
    public float calcDiscount(CommodityItem item) {
        return 0;
    }

    @Override
    public float calcSaving(CommodityItem item) {
        return contains(item.getCommodity()) ?
                item.getCommodity().getPrice() * (item.getQuantity() / 3) :
                0;
    }
}
