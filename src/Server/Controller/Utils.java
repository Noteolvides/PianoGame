package Server.Controller;

import Model.User;

public class Utils {
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

    public boolean confirmEmail(String email){
        if(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            return true;
        }
        return false;
    }
}
