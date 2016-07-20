package util;

import data.ResFile;
import data.ShopCart;
import data.TestDataCenter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sora on 2016/7/19.
 */
public class CartParserTest {

    ShopCart cart = null;

    @Before
    public void setUp() throws Exception {
        TestDataCenter.initDataManager();

        cart = CartParser.readFromJsonFile(
                ResFile.getPath("shop_cart.json"));
    }

    @Test
    public void should_read_commodity_list_in_cart_from_json_file()
            throws Exception {
        assertThat(cart.getItem("ITEM000001").getQuantity(), is(3));
        assertThat(cart.getItem("ITEM000003").getQuantity(), is(2));
    }
}