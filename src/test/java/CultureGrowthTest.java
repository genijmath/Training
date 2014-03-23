

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public class CultureGrowthTest {
        private static final double DELTA = 1e-10;

    @Test
    public void test() {
        CultureGrowth obj;
        obj = new CultureGrowth();
        assertEquals( 0.0, obj.finalTray(new int[]{0, 3, 6, 10},new int[]{0, 3, 6, 10}), DELTA);
        obj = new CultureGrowth();
        assertEquals( 0.0, obj.finalTray(new int[]{10},new int[]{240}), DELTA);
        obj = new CultureGrowth();
        assertEquals( 745.5, obj.finalTray(new int[]{10,15,3,37},new int[]{49,49,12,8}), DELTA);
    }

}

