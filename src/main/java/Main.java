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
        int googleBotCount = 0;
        int yandexBotCount = 0;
        Statistics stats = new Statistics();

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

                LogEntry logs = new LogEntry(line);
                stats.addEntry(logs);
                String userAgent = logs.getUserAgent();
                UserAgent browseAndOS = new UserAgent(logs.getUserAgent());
                System.out.println(browseAndOS);

                    if (userAgent.contains("Googlebot")) {
                        googleBotCount++;
                    } else if (userAgent.contains("YandexBot")) {
                        yandexBotCount++;
                    }
                        // C:\Users\max23\OneDrive\Рабочий стол\access.log
                    }
           }
           catch (Exception ex) {
               ex.printStackTrace();
           }

           System.out.println("Общий трафик: " + stats.getTotalTraffic());
           //System.out.println("Список существующих страниц " + stats.getPathSuccess());
            System.out.println("Список не существующих страниц " + stats.getPathFail());
           System.out.println(stats.osStatistics());
            System.out.println(stats.browserStatistics());
            System.out.println("Среднее кол-во ошибочных запросов за час " + stats.getAverageErrorsPerHour());
            System.out.println("Среднее посещяемость пользователем " + stats.getAverageVisitsPerUser());
            System.out.println("Среднее кол-во запросов за час " + stats.getAverageVisit());
           System.out.println("Средний трафик за час: " + stats.getTrafficRate());
           System.out.println("Общее количество строк в файле: " + CounterLines);
           double googleShare = (double) googleBotCount / CounterLines;
           double yandexShare = (double) yandexBotCount / CounterLines;
           System.out.println("Доля запросов от Googlebot: " + googleShare);
           System.out.println("Запросов от Googlebot: " + googleBotCount);
           System.out.println("Доля запросов от YandexBot: " + yandexShare);
           System.out.println("Запросов от YandexBot: " + yandexBotCount);}

    }
}
