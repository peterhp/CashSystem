package util;

import data.CommodityManager;
import data.ShopCart;
import data.TestDataCenter;
import info.Receipt;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sora on 2016/7/20.
 */
public class CartReceiptCalculatorTest {

    ShopCart cart;

    @Before
    public void setUp() throws Exception {
        TestDataCenter.initDataManager();
        CommodityManager commodityManager =
                CommodityManager.getManager();

        cart = new ShopCart();
        cart.add(commodityManager.get("ITEM000001"), 4);
        cart.add(commodityManager.get("ITEM000002"), 4);
        cart.add(commodityManager.get("ITEM000003"), 5);
    }

    @Test
    public void should_return_receipt_text_for_shopcart()
            throws Exception {
        Receipt receipt = CartReceiptCalculator.getReceipt(cart);

        assertThat(receipt.getPromotionsText(),
                is("买二赠一商品：\n" +
                        "名称：可口可乐，数量：1瓶\n"));
        assertThat(receipt.getTotalText(),
                is("总计：47.90（元）\n" +
                        "节省：3.60（元）\n"));
    }
}