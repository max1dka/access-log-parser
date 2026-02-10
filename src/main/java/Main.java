import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern pattern = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[(.*?)\\] \"(.*?)\" (\\d{3}) (\\d+|- ) \"(.*?)\" \"(.*?)\"");
        int googleBotCount = 0;
        int yandexBotCount = 0;

           try {
               FileReader fileReader = new FileReader(file);
               BufferedReader reader = new BufferedReader(fileReader);
               String line;

                while ((line = reader.readLine()) != null){
                CounterLines++;
                int lenght = line.length();

                if (lenght > 1024) {
                    throw new LineTooLongException("Обнаружена строка длиннее 1024 символов (длина: " + lenght + ")");
                }
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                String userAgent = matcher.group(9);
                    int start = userAgent.indexOf('(');
                    int end = userAgent.indexOf(')');
                // Если выполнять задание, и брать только первые скобки
                //    if (start != -1 && end != -1) {
                  //      String firstBrackets = userAgent.substring(start + 1, end);
                  //      String[] parts = firstBrackets.split(";");

                  //      if (parts.length >= 2) {
                 //           String fragment = parts[1].trim();
                  //          String botName = fragment.split("/")[0];
                 //           if (botName.equals("Googlebot")) {
                 //               googleBotCount++;
                 //           } else if (botName.equals("YandexBot")) {
                 //               yandexBotCount++;
                 //           }
                 //       }
                 //   }
                    if (userAgent.contains("Googlebot")) {
                        googleBotCount++;
                    } else if (userAgent.contains("YandexBot")) {
                        yandexBotCount++;
                    }
                    }
                        // C:\Users\max23\OneDrive\Рабочий стол\access.log
                    }
           }
           catch (Exception ex) {
               ex.printStackTrace();
           }
            System.out.println("Общее количество строк в файле: " + CounterLines);
            double googleShare = (double) googleBotCount / CounterLines;
            double yandexShare = (double) yandexBotCount / CounterLines;
            System.out.println("Доля запросов от Googlebot: " + googleShare);
            System.out.println("Запросов от Googlebot: " + googleBotCount);
            System.out.println("Доля запросов от YandexBot: " + yandexShare);
            System.out.println("Запросов от YandexBot: " + yandexBotCount);}
    }
}