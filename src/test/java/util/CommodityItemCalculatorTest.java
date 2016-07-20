package util;

import data.PromotionManager;
import data.ResFile;
import info.Commodity;
import info.CommodityItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Sora on 2016/7/20.
 */
public class CommodityItemCalculatorTest {

    PromotionManager promotionManager =
            PromotionManager.getManager();

    @Mock Commodity commodity;

    @Before
    public void setUp() throws Exception {
        promotionManager.readFromJsonFile(
                ResFile.getPath("promotion_list.json"));

        commodity = mock(Commodity.class);
        given(commodity.getPrice()).willReturn(3.00f);
    }

    @Test
    public void should_calc_item_cost_without_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000004");

        CommodityItemCalculator itemCalculator = new CommodityItemCalculator(
                new CommodityItem(commodity, 3));
        assertEquals(itemCalculator.calcCost(), 9.00f, 0.001f);
        assertEquals(itemCalculator.calcDiscount(), 0.00f, 0.001f);
        assertEquals(itemCalculator.calcSave(), 0.00f, 0.001f);
    }

    @Test
    public void should_calc_item_cost_only_in_buy_three_get_one_free_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000005");

        CommodityItemCalculator itemCalculator = new CommodityItemCalculator(
                new CommodityItem(commodity, 3));
        assertEquals(itemCalculator.calcCost(), 6.00f, 0.001f);
        assertEquals(itemCalculator.calcDiscount(), 0.00f, 0.001f);
        assertEquals(itemCalculator.calcSave(), 3.00f, 0.001f);
    }

    @Test
    public void should_calc_item_cost_only_in_discount_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000002");

        CommodityItemCalculator itemCalculator = new CommodityItemCalculator(
                new CommodityItem(commodity, 3));
        assertEquals(itemCalculator.calcCost(), 8.55f, 0.001f);
        assertEquals(itemCalculator.calcDiscount(), 0.45f, 0.001f);
        assertEquals(itemCalculator.calcSave(), 0.45f, 0.001f);
    }

    @Test
    public void should_calc_item_cost_in_both_promotions()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000001");

        CommodityItemCalculator itemCalculator = new CommodityItemCalculator(
                new CommodityItem(commodity, 3));
        assertEquals(itemCalculator.calcCost(), 6.00f, 0.001f);
        assertEquals(itemCalculator.calcDiscount(), 0.00f, 0.001f);
        assertEquals(itemCalculator.calcSave(), 3.00f, 0.001f);
    }
}