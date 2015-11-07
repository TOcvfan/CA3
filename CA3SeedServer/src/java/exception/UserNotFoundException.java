
package exception;

/**
 *
 * @author plaul1
 */
public class UserNotFoundException extends Exception {

  public UserNotFoundException(String string) {
    super(string);
  }
  public UserNotFoundException() {
    super("User with requested username not found");
  }
  
}
