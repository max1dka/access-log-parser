import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    HashSet<String> pathSuccess = new HashSet<>();
    HashMap<String, Integer> osCountMap = new HashMap<>();



    public Statistics(){
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;

    }


    public void addEntry(LogEntry logs){
        this.totalTraffic += logs.getResponseSize();
        if (logs.getTime().isBefore(minTime)) {
            minTime = logs.getTime();
        }
        if (logs.getTime().isAfter(maxTime)) {
            maxTime = logs.getTime();
        }
        if (logs.getResponseCode() == 200){
            pathSuccess.add(logs.getPath());
        }
        UserAgent ua = new UserAgent(logs.getUserAgent());
        String osName = ua.getOperatingSystem().toString();
        if (osCountMap.containsKey(osName)) {
            osCountMap.put(osName, osCountMap.get(osName) + 1);
        } else {
            osCountMap.put(osName, 1);
        }

    }

    public HashMap<String, Double> osStatistics(){
        HashMap<String, Double> osShares = new HashMap<>();

        // Считаем общее количество всех ОС
        int totalOSCount = 0;
        for (int count : osCountMap.values()) {
            totalOSCount += count;
        }

        // Заполняем новую мапу долями
        for (Map.Entry<String, Integer> entry : osCountMap.entrySet()) {
            double share = (double) entry.getValue() / totalOSCount;
            osShares.put(entry.getKey(), share);
        }

        return osShares;
    }

    public double getTrafficRate() {
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1;
        return (double) totalTraffic / hours;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public HashSet<String> getPathSuccess() {
        return pathSuccess;
    }
}
