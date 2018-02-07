import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.System.out;


public class Write{
    public static void main(String[] args) {
        String destfile = "data/nima";

        try {
             PrintWriter out = new PrintWriter(new File(destfile));
             PrintWriter outEnd = new PrintWriter(new FileOutputStream(destfile, true));

            out.println("salam");
            outEnd.println("salam");
            out.close();
            outEnd.close();

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            out.println("no file");

        }
    }
}