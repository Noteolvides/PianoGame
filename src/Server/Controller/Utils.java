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
        //TODO: HE CAMBIADO LOS REGEX PORQUE SINO SIEMPRE ERA INCORRECTA
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
        //TODO: Ni idea de lo que se busca aqui :(
        if (password.matches("(?=.*d)")) {
            num++;
        }
        return num >= 2;
    }
}
