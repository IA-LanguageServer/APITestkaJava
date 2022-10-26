package bind.util;

import utils.driver_manager.DriverManager;

public class Util {

    private final DriverManager driverManager;

    public Util(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public String generateHtml() {
        return driverManager.sendCommand(
                "[[\"generate_html\"]]"
        );
    }

    public String generateHtml(String htmlName) {
        return driverManager.sendCommand(
                String.format("[[\"generate_html\", {\"html_name\": \"%s\"}]]", htmlName)
        );
    }

}
