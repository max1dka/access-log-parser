import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

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
    }

    public double getTrafficRate() {
        long hours = ChronoUnit.HOURS.between(minTime, maxTime);
        if (hours == 0) hours = 1;
        return (double) totalTraffic / hours;
    }

    public int getTotalTraffic() {
        return totalTraffic;
    }
}
