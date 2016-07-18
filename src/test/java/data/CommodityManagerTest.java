package data;

import info.Commodity;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Sora on 2016/7/18.
 */
public class CommodityManagerTest {

    @Test
    public void should_read_enough_commodity_from_json_file()
            throws Exception {
        CommodityManager commodityManager = CommodityManager.getManager();
        commodityManager.readFromJsonFile(getClass().getClassLoader()
                .getResource("commodity_list.json").getPath());

        assertThat(commodityManager.getCount(), is(6));
    }

    @Test
    public void should_read_right_commodity_from_json_file()
            throws Exception {
        CommodityManager commodityManager = CommodityManager.getManager();
        commodityManager.readFromJsonFile(getClass().getClassLoader()
                .getResource("commodity_list.json").getPath());

        Commodity commodity = commodityManager.get("ITEM000002");
        assertThat(commodity.getName(), is("雪碧"));
        assertThat(commodity.getCategory(), is("食品"));
        assertThat(commodity.getSubCategory(), is("饮料"));
        assertThat(commodity.getUnit(), is("瓶"));
        assertThat(commodity.getPrice(), is(3.00f));
    }
}