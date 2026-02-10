import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LogEntry {
  final String ipAddr;
  final LocalDateTime time;
  final HTTPMethods method;
  final String path;
  final int responseCode;
  final int responseSize;
  final String referer;
  final String userAgent;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
    private static final Pattern pattern = Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[(.*?)\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+|-) \"(.*?)\" \"(.*?)\"");

    public LogEntry(String line) {

        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {

            this.ipAddr = matcher.group(1);


            this.time = ZonedDateTime.parse(matcher.group(4), formatter).toLocalDateTime();

            String methodString = matcher.group(5);
            this.method = HTTPMethods.valueOf(methodString);

            this.path = matcher.group(6);

          //  System.out.println("Код ответа: " + matcher.group(6));
            this.responseCode = Integer.parseInt(matcher.group(8));

          //  System.out.println("Размер данных: " + matcher.group(7));
            this.responseSize = Integer.parseInt(matcher.group(9));

          //  System.out.println("Referer: " + matcher.group(8));
            this.referer = matcher.group(10);

         //   System.out.println("User-Agent: " + matcher.group(9));
            this.userAgent = matcher.group(11);
        } else {
            System.out.println("Строка не соответствует формату");
           // System.out.println("IP адрес: " + matcher.group(1));
            this.ipAddr = " ";
            this.method = HTTPMethods.UNKNOWN;
          //  System.out.println("Дата и время: " + matcher.group(4));
            this.time = LocalDateTime.parse("2025-02-10T14:30:00");

          //  System.out.println("Запрос (метод и путь): " + matcher.group(5));
            this.path = " ";

          //  System.out.println("Код ответа: " + matcher.group(6));
            this.responseCode = 0;

          //  System.out.println("Размер данных: " + matcher.group(7));
            this.responseSize = 0;

          //  System.out.println("Referer: " + matcher.group(8));
            this.referer = " ";

          //  System.out.println("User-Agent: " + matcher.group(9));
            this.userAgent = " ";
        }
    }

    public String getIpAddr(){
        return this.ipAddr;
    }
    public LocalDateTime getTime(){
        return this.time;
    }
    public HTTPMethods getMethod(){
        return this.method;
    }
    public String getpath(){
        return this.path;
    }
    public int getCode(){
        return this.responseCode;
    }
    public int getResponseSize(){
        return this.responseSize;
    }
    public String getReferer(){
        return this.referer;
    }
    public String getUserAgent(){
        return this.userAgent;
    }

    @Override
    public String toString() {
        return "IP адрес: " + ipAddr + " Дата и время: " + time + " Метод: " + method + " Путь: " + path + " Код ответа: " + responseCode + " Размер: " + responseSize + " referer: " + referer + " UserAgent: " + userAgent;
    }
}







