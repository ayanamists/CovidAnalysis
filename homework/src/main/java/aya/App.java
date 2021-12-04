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
//            var t2Week=f.task2Week("China");
//            var t2Month = f.task2Month("China");
//            var t3=f.task3();
//            System.out.println(t2Week);
//            System.out.println(t2Month);
            System.out.println(list.toString());
            f.ascendingsorting(list.toString());
            f.descendingsorting(list.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
