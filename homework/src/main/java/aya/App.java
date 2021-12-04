package aya;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            List<String> list = new ArrayList<>();
            Functional f = new Functional();
            var t1 = f.task1();
            list.add("country('China', 19)");
            list.add("country('US', 20)");
            list.add("country('UK', 15)");
//            var t2Week=f.task2Week("China");
//            var t2Month = f.task2Month("China");
//            var t3=f.task3();
//            System.out.println(t2Week);
//            System.out.println(t2Month);
            f.ascendingSorting(list.toString());
            // f.descendingsorting(list.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
