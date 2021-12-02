package aya;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Functional f = new Functional();
            var t1 = f.task1();
            t1.forEach((k, v) -> {
                System.out.println(k + " : " + v.toString());
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
