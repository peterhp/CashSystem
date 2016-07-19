package data;

import info.Buy3Free1Promotion;
import info.Commodity;
import info.DiscountPromotion;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Sora on 2016/7/18.
 */
public class PromotionManagerTest {

    PromotionManager promotionManager =
            PromotionManager.getManager();

    @Before
    public void setUp() throws Exception {
        promotionManager.readFromJsonFile(
                ResFile.getPath("promotion_list.json"));
    }

    @Test
    public void should_read_enough_promotion_from_json_file()
            throws Exception {
        assertThat(promotionManager.count(), is(2));
    }

    @Test
    public void should_get_promotion_for_certain_commodity()
            throws Exception {
        Commodity commodity = mock(Commodity.class);

        given(commodity.getBarcode()).willReturn("ITEM000003");
        assertNull(promotionManager.getPromotion(commodity));

        given(commodity.getBarcode()).willReturn("ITEM000005");
        assertThat(promotionManager.getPromotion(commodity)
                instanceof Buy3Free1Promotion, is(true));

        given(commodity.getBarcode()).willReturn("ITEM000006");
        assertThat(promotionManager.getPromotion(commodity)
                instanceof DiscountPromotion, is(true));

        given(commodity.getBarcode()).willReturn("ITEM000001");
        assertThat(promotionManager.getPromotion(commodity)
                instanceof Buy3Free1Promotion, is(true));
    }
}