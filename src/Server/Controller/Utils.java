package Server.Controller;


/**
 * Utils Class purpose's to offer Regular Expressions (Regex)
 * for the log-in and register functions. It determines whether
 * whether the information introduced by the user is its correct
 * format or no
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class Utils {
    /**
     * Determines whether the password entered by user is valid or not
     * @param password: Password introduced by the user
     * @param confirmPassword: Password confirmation
     * @param user: User
     * @return Password matches or not
     */
    public boolean confirmPassword(String password, String confirmPassword, String user) {
        boolean validPassword;
        validPassword = password.equals(confirmPassword);
        if (validPassword) {
            validPassword = password.length() >= 8;
            if (validPassword) {
                validPassword = !password.contains(user);
                if (validPassword) {
                    validPassword = specialCharacters(password);
                }
            }
        }

        return validPassword;
    }

    /**
     * Counts the number of special Characters
     * @param password: Special Characters of Password
     * @return Number of Special Characters
     */
    private boolean specialCharacters(String password) {
        int num = 0;
        if (password.matches(".*[a-z].*")) {
            num++;
        }
        if (password.matches(".*[A-Z].*")) {
            num++;
        }
        if (password.matches("(.*[@#$%^&+=].*)")) {
            num++;
        }
        if (password.matches("(.*[0-9]*.)")) {
            num++;
        }
        return num >= 2;
    }

    /**
     * Regex that determines whether the format of the email is correct or not
     * @param email: Email entered by the user
     * @return a boolean if the email is confirm
     */
    public boolean confirmEmail(String email){
        if(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return true;
        }
        return false;
    }
}
