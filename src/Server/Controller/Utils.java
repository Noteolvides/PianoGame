package Server.Controller;

import Model.User;


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
     * @return
     */
    public boolean confirmEmail(String email){
        if(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return true;
        }
        return false;
    }
}
