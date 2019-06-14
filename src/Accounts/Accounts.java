package Accounts;

import java.io.*;
import java.util.Scanner;

public class Accounts implements InterfaceForAccounts {

    private String username, password, email;
    private BufferedReader input;
    private Scanner scanner;

    public Accounts() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        input = new BufferedReader(inputStreamReader);
    }

    private Accounts(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    private String getEmail() {
        return email;
    }

    @Override
    public void login() throws IOException {
        System.out.print("Enter your username: ");
        String enteredUser = input.readLine();

        System.out.print("Enter your password: ");
        String enteredPass = input.readLine();

        DatabaseProcesses objForCall = new DatabaseProcesses();

        if (objForCall.checkForLogin(enteredUser.trim(), enteredPass.trim())) {
            //Login successful olanda getmeli prosesler burda yazilmali
            System.out.println("Welcome to your account!");
        }
    }

    @Override
    public void register() throws IOException {
        DatabaseProcesses objForCall = new DatabaseProcesses();

        System.out.print("Enter an username: ");
        String registeredUser = input.readLine();

        while (true) {
            String symbol = "-";
            System.out.print("Enter an email: ");
            String registeredEmail = input.readLine();

            if (registeredEmail.contains("@") && registeredEmail.contains(".")) {

                String password = passConfirming();

                boolean[] array = objForCall.checkForRegistration(registeredUser, registeredEmail);

                if (array[0] && array[1]) {

                    Accounts newAccount = new Accounts(registeredUser, password, registeredEmail);
                    objForCall.addRecord(newAccount.getUsername(), newAccount.getPassword(), newAccount.getEmail());

                    break;
                }

                if (!array[0]) {
                    System.out.println("This username is used");
                }
                if (!array[1]) {
                    System.out.println("This email is used");
                }
                break;
            } else {
               // System.out.println(symbol.repeat(20) + "\nPlease enter a real mail address. Try again:");
            }
        }
    }

    private String passConfirming() throws IOException {

        String password = null;
        String symbol = "-";

        System.out.print("Enter a password: ");
        String password_1 = input.readLine();

        System.out.print("Confirm password: ");
        String password_2 = input.readLine();

        if (password_1.equals(password_2)) {

            if (password_1.length() <= 20) password = password_1;
            else {
               // System.out.println(symbol.repeat(20) + "\nLength of password characters can not be greater than 20! Try again:");
                passConfirming();
            }
        } else {
           // System.out.println(symbol.repeat(20) + "\nPassword confirming is not correct! Try again:");
            passConfirming();
        }
        return password;
    }
}