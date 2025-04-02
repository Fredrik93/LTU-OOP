package freull0;

import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Testing
{
    private static final Logger log = Logger.getLogger(Testing.class.getName());

    public static void main(String[] args)
    {
        Testing t = new Testing();
        t.checkBits();
    }

    private void checkBits()
    {
        BitSet s1 = new BitSet();
        BitSet s2 = new BitSet();
        s1.set(0);
        s1.set(3);
        s2.set(4);
        System.out.println("setting up set 1:");
        for(int i=0; i<s1.length(); i++){
                System.out.print(s1.get(i) ? "1 " : "0 ");
            }

        System.out.println("\n*****\nsetting up set 2:");
        for(int i=0; i<s2.length(); i++){
            System.out.print(s2.get(i) ? "1 " : "0 ");
        }
        System.out.println("\n\nResult of operations\n______________ \n");

        //index:0 set1 borde inte ha kvar sin 1a efter s2.and(s1)
        s2.and(s1);
        for(int i=0; i<s1.length(); i++){
            System.out.print(s1.get(i) ? "1 " : "0 ");
        }
        System.out.println("\n");
        for(int i=0; i<s2.length(); i++){
            System.out.print(s2.get(i) ? "1 " : "0 ");
        }


    }

}
