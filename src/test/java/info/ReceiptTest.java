package info;

import data.TestDataCenter;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sora on 2016/7/20.
 */
public class ReceiptTest {

    CommodityItem item = new CommodityItem(
            TestDataCenter.createCommodity(), 3);

    @Test
    public void should_return_item_text_in_receipt()
            throws Exception {
        assertThat(Receipt.itemText(item, 9.00f, 0.00f),
                is("名称：可口可乐，数量：3瓶，单价：3.00（元），" +
                        "小计：9.00（元）"));
        assertThat(Receipt.itemText(item, 6.00f, 0.00f),
                is("名称：可口可乐，数量：3瓶，单价：3.00（元），" +
                        "小计：6.00（元）"));
        assertThat(Receipt.itemText(item, 8.55f, 0.45f),
                is("名称：可口可乐，数量：3瓶，单价：3.00（元），" +
                        "小计：8.55（元），节省0.45（元）"));
    }
}