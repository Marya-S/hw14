import exception.WrongLoginException;
import exception.WrongPasswordException;
import exception.WrongSymbolsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите login:");
        String login = reader.readLine();
        System.out.println("Введите password:");
        String password = reader.readLine();
        System.out.println("Подтвердите password:");
        String confirmPassword = reader.readLine();
        boolean resultCheck = method(login,password,confirmPassword);
        System.out.println(resultCheck);
    }

    public static boolean method(String login, String password, String confirmPassword) {
        try {
            checkSymbol(login);
            checkSymbol(password);
        } catch (WrongLoginException | WrongSymbolsException e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            checkPassword(password, confirmPassword);
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static void checkSymbol(String text) {
        String regex = "^[A-Za-z0-9+_]+$";
        if (!Pattern.matches(regex, text)) {
            throw new WrongSymbolsException("Exception: Symbol is wrong");
        }
        if (text.length() > 20) {
            throw new WrongLoginException("Exception: Length is wrong");
        }
    }

    private static void checkPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Exception: Passwords don't match");
        }
    }
}