import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    HashSet<String> pathSuccess = new HashSet<>();
    HashSet<String> pathFail = new HashSet<>();
    HashMap<String, Integer> osCountMap = new HashMap<>();
    HashMap<String, Integer> browserCountMap = new HashMap<>();
    private int userVisits = 0;
    private int errorRequests = 0;
    private HashSet<String> uniqueUsers = new HashSet<>();



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
        if (logs.getResponseCode() == 404){
            pathFail.add(logs.getPath());
        }


        UserAgent ua = new UserAgent(logs.getUserAgent());
       String browserName = ua.getBrowser().toString();
       if (browserCountMap.containsKey(browserName)){
           browserCountMap.put(browserName, browserCountMap.get(browserName) + 1);
       } else {
           browserCountMap.put(browserName, 1);
       }

        if (!ua.isBot()) {
            this.userVisits++;
            this.uniqueUsers.add(logs.getIpAddr());
        }

        if (logs.getResponseCode() >= 400 && logs.getResponseCode() < 600) {
            this.errorRequests++;
        }


        String osName = ua.getOperatingSystem().toString();
        if (osCountMap.containsKey(osName)) {
            osCountMap.put(osName, osCountMap.get(osName) + 1);
        } else {
            osCountMap.put(osName, 1);
        }

    }
    public HashMap<String, Double> browserStatistics(){
        HashMap<String, Double> browserShares = new HashMap<>();


        int totalOSCount = 0;
        for (int count : browserCountMap.values()) {
            totalOSCount += count;
        }

        for (Map.Entry<String, Integer> entry : browserCountMap.entrySet()) {
            double share = (double) entry.getValue() / totalOSCount;
            browserShares.put(entry.getKey(), share);
        }

        return browserShares;
    }
    public HashMap<String, Double> osStatistics(){
        HashMap<String, Double> osShares = new HashMap<>();


        int totalOSCount = 0;
        for (int count : osCountMap.values()) {
            totalOSCount += count;
        }


        for (Map.Entry<String, Integer> entry : osCountMap.entrySet()) {
            double share = (double) entry.getValue() / totalOSCount;
            osShares.put(entry.getKey(), share);
        }

        return osShares;
    }
    public double getAverageVisit() {
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1;
        return (double) userVisits / hours;
    }

    public double getAverageErrorsPerHour() {
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1;
        return (double) errorRequests / hours;
    }

    public double getAverageVisitsPerUser() {
        if (uniqueUsers.isEmpty()) return 0;
        return (double) userVisits / uniqueUsers.size();
    }

    public double getTrafficRate() {
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1;
        return (double) totalTraffic / hours;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }

    public Set<String> getPathSuccess() {
        return pathSuccess;
    }
    public Set<String> getPathFail() {
        return pathFail;
    }
}
