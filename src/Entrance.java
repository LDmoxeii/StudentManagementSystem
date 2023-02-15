import java.util.Scanner;

public class Entrance {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        System.out.println("-----------Welcome Students Management System---------");

        System.out.println("1.insert\t2.deleteById\n3.updateById\t4.selectAll\n5.select\t6.exit");

        functionalServices();
    }

    public static void functionalServices() {
        switch (scanner.nextLine()) {
            case "1":
                System.out.println(Function.insert());
                break;
            case "2":
                System.out.println(Function.deleteById());
                break;
            case "3":
                System.out.println(Function.updateById());
                break;
            case "4":
                System.out.println(Function.selectAll());
                break;
            case "5":
                System.out.println(Function.select());
                break;
            case "6":
                System.exit(0);
                break;

        }
    }
}
