
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author deme
 */
public class Main {
    public static void main(String[] args) throws IOException
    {
        if(args[0].equalsIgnoreCase("ppc"))
        {
             ppc.Test.main();
        }else if(args[0].equalsIgnoreCase("rpt"))
        {
            representations.Test.main();
        }else if(args[0].equalsIgnoreCase("plan"))
        {
             planning.Main.main();
        }else if(args[0].equalsIgnoreCase("extract"))
        {
            datamining.Main.main();
        }else if(args[0].equalsIgnoreCase("heal")){
		
		Exemple.Test.main();
	}
            
    }
}
