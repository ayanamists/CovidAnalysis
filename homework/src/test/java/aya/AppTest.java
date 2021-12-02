package aya;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        try {
            Functional f = new Functional();
            var t1 = f.task2Week("Cambodia", 2020, 5);
            assertEquals(1, t1);

            assertEquals(80823, f.task2Week("China", 2020, 10));

            assertEquals(1, f.task2Month("Cambodia", 2020, 1));
            assertEquals(0, f.task2Month("Haiti", 2020, 1));
            assertEquals(15, f.task2Month("Haiti", 2020, 3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
