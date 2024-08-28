
package myPack;

/**
 *
 * @author u274612
 */

import java.util.Random; 


public class LoadSalt  {
    
    
    public String getToken(){
        
    Random rand = new Random(); 
    long value = rand.nextLong();
    String token=String.valueOf(value);
        // Print random integers 
        System.out.println("Random Integers: "+token); 
        return token;
    }
    
}