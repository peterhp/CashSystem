package util;

import data.ShopCart;
import info.CommodityItem;
import info.Receipt;

import java.util.List;

/**
 * Created by Sora on 2016/7/20.
 */
public class CartReceiptCalculator {

    public static Receipt getReceipt(ShopCart cart) {
        List<CommodityItem> itemList = cart.getItems();
        Receipt receipt = new Receipt();

        float cost = 0, save = 0;
        for (CommodityItem item : itemList) {
            CommodityItemCalculator itemCalculator =
                    new CommodityItemCalculator(item);

            cost += itemCalculator.calcCost();
            save += itemCalculator.calcSave();

            receipt.addItem(item, itemCalculator.calcCost(),
                    itemCalculator.calcDiscount());

            if (itemCalculator.getItemPromotion() != null) {
                receipt.addItemPromotion(item,
                        itemCalculator.getItemPromotion());
            }
        }
        receipt.setTotal(cost, save);

        return receipt;
    }
}
