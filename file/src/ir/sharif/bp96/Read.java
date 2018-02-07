import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.out;

public class Read{
	public static void main(String[] args){

	    String srcfile = "data/nima.txt";
        Scanner inp = null;
        try {
            inp = new Scanner(new FileInputStream(srcfile));
            while(inp != null && inp.hasNext())
		    {
    			out.println(inp.next());
    		}
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            out.println("no file");
        }
        File here = new File(".");
		out.println(here.isDirectory());



	}

}
