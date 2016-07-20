package info;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Sora on 2016/7/19.
 */
public class PromotionTest {

    Promotion buy3free1Promotion = new Buy3Free1Promotion();
    Promotion discountPromotion = new DiscountPromotion();

    @Mock Commodity commodity;

    @Before
    public void setUp() throws Exception {
        buy3free1Promotion.add("ITEM000001");
        discountPromotion.add("ITEM000001");

        commodity = mock(Commodity.class);
        given(commodity.getPrice()).willReturn(3.00f);
    }

    @Test
    public void should_check_whether_commodity_in_buy_three_get_one_free_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(buy3free1Promotion.contains(commodity), is(true));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(buy3free1Promotion.contains(commodity), is(false));
    }

    @Test
    public void should_get_right_saving_for_buy_three_get_one_free_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(buy3free1Promotion.calcSaving(
                new CommodityItem(commodity, 2)), is(0.00f));
        assertThat(buy3free1Promotion.calcSaving(
                new CommodityItem(commodity, 3)), is(3.00f));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(buy3free1Promotion.calcSaving(
                new CommodityItem(commodity, 3)), is(0.00f));
    }

    @Test
    public void should_check_whether_commodity_in_five_percent_discount_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(discountPromotion.contains(commodity), is(true));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(discountPromotion.contains(commodity), is(false));
    }

    @Test
    public void should_get_right_saving_for_five_percent_discount_promotion()
            throws Exception {
        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(discountPromotion.calcSaving(
                new CommodityItem(commodity, 2)), is(0.30f));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(discountPromotion.calcSaving(
                new CommodityItem(commodity, 3)), is(0.00f));
    }
}