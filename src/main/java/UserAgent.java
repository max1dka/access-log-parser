import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private final OperatingSystem operatingSystem;
    private final Browser browser;

    private static final Pattern OS_INFO_PATTERN = Pattern.compile("^.*?\\(([^)]*)\\)");


    public UserAgent(String userAgentString) {

        if (userAgentString.equals("-") || userAgentString.trim().isEmpty()) {
            this.operatingSystem = OperatingSystem.UNKNOWN;
            this.browser = Browser.UNKNOWN;
            return;
        }


        String os = "Unknown";
        Matcher osMatcher = OS_INFO_PATTERN.matcher(userAgentString);
        if (osMatcher.find()) {
            String osInfo = osMatcher.group(1);
            if (osInfo.contains("Windows")) {
                os = "Windows";
            } else if (osInfo.contains("Mac OS")) {
                os = "macOS";
            } else if (osInfo.contains("Linux")) {
                os = "Linux";
            } else {
                os = "Unknown";
            }
        }
        this.operatingSystem = OperatingSystem.valueOf(os.toUpperCase());

        Browser foundBrowser = Browser.UNKNOWN;

        if (userAgentString.contains("Googlebot")) {
            foundBrowser = Browser.GOOGLEBOT;
        } else if (userAgentString.contains("YandexBot")) {
            foundBrowser = Browser.YANDEXBOT;
        } else if (userAgentString.contains("Chrome")) {
            foundBrowser = Browser.CHROME;
        } else if (userAgentString.contains("Firefox")) {
            foundBrowser = Browser.FIREFOX;
        } else if (userAgentString.contains("Opera")) {
            foundBrowser = Browser.OPERA;
        } else if (userAgentString.contains("Edge")) {
            foundBrowser = Browser.EDGE;
        } else if (userAgentString.contains("Safari")) {
            foundBrowser = Browser.SAFARI;
        }
        this.browser = foundBrowser;
    }


    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public Browser getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "OS: " + operatingSystem + ", Browser: " + browser;
    }
}
