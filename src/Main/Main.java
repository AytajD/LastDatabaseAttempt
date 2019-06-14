package Main;

import Accounts.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("1. Login\n2. Register");

        Scanner scanner = new Scanner(System.in);

        byte choice = scanner.nextByte();

        Accounts objForCall = new Accounts();

        switch (choice) {
            case 1:
                objForCall.login();
                break;
            case 2:
                objForCall.register();
                break;
            default:
                System.out.println("This choice is not exist!");
        }
    }
}
