package data;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Sora on 2016/7/18.
 */
public class PromotionManagerTest {

    PromotionManager promotionManager = PromotionManager.getManager();

    @Before
    public void setUp() throws Exception {
        promotionManager.readFromJsonFile(getClass().getClassLoader()
                .getResource("promotion_list.json").getPath());
    }

    @Test
    public void should_read_enough_promotion_from_json_file()
            throws Exception {
        assertThat(promotionManager.count(), is(2));
    }
}