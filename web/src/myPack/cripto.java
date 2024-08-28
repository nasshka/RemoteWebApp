
package myPack;

/**
 *
 * @author u274612
 */
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
  
public class cripto { 
    
    public static void main(String[] args){  encryptString("wnspass");  }
    
    public static String encryptString(String input) 
    { 
        
        try { 
            
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
                  } 
            System.out.println(hashtext);
              return hashtext; 
            } 
  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
   
} }  

 