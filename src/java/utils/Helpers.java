
package utils;
import kupac.*;
/**
 *
 * @author malenicn
 */
public class Helpers {
    public static String getId() {
        return "id = '" + Kupac.kupac.getUsername() + Kupac.kupac.getPassword() + "'";
    }
    
    public static String getId(String username, String password) {
        return "id = '" + username + password + "'";
    }
    
}
