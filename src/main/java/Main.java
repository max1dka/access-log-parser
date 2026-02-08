import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LineTooLongException {
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
            int CounterLines = 0;
            int min = Integer.MAX_VALUE;
            int max = 0;

           try {
               FileReader fileReader = new FileReader(file);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null){
                CounterLines++;
                int lenght = line.length();
                if (lenght > 1024) {
                    throw new LineTooLongException("Обнаружена строка длиннее 1024 символов (длина: " + lenght + ")");
                }
                if (lenght > max ){
                    max = lenght;
                }
                if (lenght < min){
                    min = lenght;
                }
            }   System.out.println("Длина самой короткой строки:" + min);
                System.out.println("Длина самой длинной строки:" + max);
                System.out.println("Общее количество строк в файле: " + CounterLines);


           } // C:\Users\max23\OneDrive\Рабочий стол\access.log
           catch (Exception ex) {
               ex.printStackTrace();
           }
        }


    }
}
