import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите ваше первое число:");
        int number = new Scanner(System.in) .nextInt();
        System.out.println("Введите ваше второе число:");
        int second_number = new Scanner(System.in) .nextInt();
        int summ = number + second_number;
        int diff = number - second_number;
        int umn = number * second_number;
        double delenie = (double) number / second_number;
        System.out.println( "Сумма чисел равна " + summ);
        System.out.println( "Разность чисел равна " + diff);
        System.out.println( "Умножение чисел равно " + umn);
        System.out.println( "Деление чисел равно " + delenie);
      }
}
