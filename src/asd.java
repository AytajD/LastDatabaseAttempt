import java.util.Scanner;

public class asd {

    static void def(int ... numbers) {
        int sum = 0;

        for(int i : numbers) sum += i;

        System.out.println(sum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        if(str.startsWith("a")) System.out.println(str);
    }
}
