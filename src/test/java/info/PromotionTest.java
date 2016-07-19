package info;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Sora on 2016/7/19.
 */
public class PromotionTest {

    @Test
    public void should_check_whether_commodity_in_buy_three_get_one_free_promotion()
            throws Exception {
        Promotion buy3free1Promotion = new Promotion("BUY_THREE_GET_ONE_FREE");
        buy3free1Promotion.add("ITEM000001");

        Commodity commodity = mock(Commodity.class);

        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(buy3free1Promotion.contains(commodity), is(true));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(buy3free1Promotion.contains(commodity), is(false));
    }

    @Test
    public void should_get_right_discount_for_buy_three_get_one_free_promotion()
            throws Exception {
        Promotion buy3free1Promotion = new Promotion("BUY_THREE_GET_ONE_FREE");
        buy3free1Promotion.add("ITEM000001");

        Commodity commodity = mock(Commodity.class);
        given(commodity.getPrice()).willReturn(3.00f);

        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(buy3free1Promotion.calcDiscount(commodity, 2), is(0.00f));
        assertThat(buy3free1Promotion.calcDiscount(commodity, 3), is(3.00f));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(buy3free1Promotion.calcDiscount(commodity, 3), is(0.00f));
    }

    @Test
    public void should_check_whether_commodity_in_five_percent_discount_promotion()
            throws Exception {
        Promotion discountPromotion = new Promotion("FIVE_PERCENT_DISCOUNT");
        discountPromotion.add("ITEM000001");

        Commodity commodity = mock(Commodity.class);

        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(discountPromotion.contains(commodity), is(true));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(discountPromotion.contains(commodity), is(false));
    }

    @Test
    public void should_get_right_discount_for_five_percent_discount_promotion()
            throws Exception {
        Promotion discountPromotion = new Promotion("FIVE_PERCENT_DISCOUNT");
        discountPromotion.add("ITEM000001");

        Commodity commodity = mock(Commodity.class);
        given(commodity.getPrice()).willReturn(3.00f);

        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(discountPromotion.calcDiscount(commodity, 2), is(0.30f));

        given(commodity.getBarcode()).willReturn("ITEM000002");
        assertThat(discountPromotion.calcDiscount(commodity, 3), is(0.00f));
    }
}