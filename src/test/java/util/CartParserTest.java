package util;

import data.ResFile;
import data.ShopCart;
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
        cart = CartParser.readFromJsonFile(
                ResFile.getPath("shop_cart.json"));
    }

    @Test
    public void should_read_commodity_list_in_cart_from_json_file()
            throws Exception {
        assertThat(cart.get("ITEM000001"), is(3));
        assertThat(cart.get("ITEM000003"), is(2));
    }
}