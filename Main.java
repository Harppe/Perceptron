import java.util.*;
import java.io.*;
public class Main
{
    public static void main(String[] args) {
        System.out.print("\f");
        Network net = new Network();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arguments = null;
        System.out.print(">>>");
        try {
            arguments = reader.readLine();
        }
        catch (java.io.IOException exc) {
            System.out.println(exc.getMessage());   
        }
        System.out.println("Before training: ");
        net.findValue(arguments);
        System.out.println("Edge weights: " + printFloatArray(net.edges));
        net.sequence();
        System.out.println("\n\nAfter training: ");
        net.findValue(arguments);
        System.out.println("Edge weights: " + printFloatArray(net.edges));
    }

    public static String printFloatArray(float[] arr) {
        String str = "";
        for (float f : arr) {
            str+=f+" ";
        }
        return str;
    }
}

