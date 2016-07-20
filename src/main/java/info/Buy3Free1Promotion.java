package info;

/**
 * Created by Sora on 2016/7/19.
 */
public class Buy3Free1Promotion extends Promotion {

    public int calcFreeQuantity(CommodityItem item) {
        return contains(item.getCommodity()) ?
                item.getQuantity() / 3 : 0;
    }

    @Override
    public float calcDiscount(CommodityItem item) {
        return 0;
    }

    @Override
    public float calcSaving(CommodityItem item) {
        return item.getCommodity().getPrice()
                * calcFreeQuantity(item);
    }

    @Override
    public String getPromotionTitle() {
        return "买二赠一商品：";
    }

    @Override
    public String getItemPromotion(CommodityItem item) {
        if (calcFreeQuantity(item) > 0) {
            return String.format("名称：%s，数量：%d%s",
                    item.getCommodity().getName(),
                    calcFreeQuantity(item),
                    item.getCommodity().getUnit());
        }
        return "";
    }
}
