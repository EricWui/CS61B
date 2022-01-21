import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        Integer a = 128, b = 128;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
