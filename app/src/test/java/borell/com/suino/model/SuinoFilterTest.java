package borell.com.suino.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import borell.com.suino.BuildConfig;
import borell.com.suino.TestJson;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,constants = BuildConfig.class)
public class SuinoFilterTest {
    @Test
    public void test_createSearchJson(){
        double latitude = 41.3802437;
        double longitude = 2.1590683;
        String category = "sport";
        int groupSize = 1;
        int level_1 = 1;
        int level_2 = 2;
        int price = 15;
        int maxDistance = 25;
        String keyword = "beachvolley";
        SuinoFilter filter = new SuinoFilter(latitude, longitude);
        filter.setCategoryFilter(category);
        filter.setGroupFilter(groupSize);
        filter.addLevelFilter(level_1);
        filter.addLevelFilter(level_2);
        filter.setPriceFilter(price);
        filter.setMaxDistanceFilter(maxDistance);
        filter.addKeyword(keyword);

        String result = filter.createSearchJson();

        assertEquals(TestJson.searchJson, result);


    }
}
