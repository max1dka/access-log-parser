import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ways = 0;
        while (true) {
        String path = new Scanner(System.in).nextLine();
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        boolean fileExists = file.exists();
            System.out.println(isDirectory);
            System.out.println(fileExists);
        if (isDirectory) {
            System.out.println("Данный адрес не ведет к файлу (вы указали путь к папке) ");
            continue;
        }

        if (!fileExists) {
            System.out.println("Указаного файла не существует или введено некорректное значение");
            continue;
        }

        ways += 1;
        System.out.println("Путь указан верно! Файл существует!");
        System.out.println("Верно указанных путей: " + ways);

        }

    }
}
