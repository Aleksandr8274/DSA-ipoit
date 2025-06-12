package by.it.group410971.maleckij.lesson06;

import org.junit.Test;
import java.io.InputStream;
import static org.junit.Assert.assertTrue;

public class Lesson06Test {
    @Test
    public void checkA() throws Exception {
        InputStream inputStream = A_LIS.class.getResourceAsStream("dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(inputStream);
        assertTrue("A failed", result == 3);
    }

    @Test
    public void checkB() throws Exception {
        InputStream inputStream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(inputStream);
        assertTrue("B failed", result == 3);
    }

    @Test(timeout = 1000)
    public void checkC() throws Exception {
        InputStream inputStream = C_LongNotUpSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(inputStream);
        assertTrue("C failed", result == 4);
    }
}