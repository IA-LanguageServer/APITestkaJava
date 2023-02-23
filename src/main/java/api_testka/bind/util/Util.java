package api_testka.bind.util;

import api_testka.utils.driver_manager.DriverManager;

public class Util {

    private final DriverManager driverManager;

    /**
     * class init with driver manager
     * @param driverManager driver manager that manage driver:
     * */
    public Util(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    /**
     * generate html report
     * @return server response string
     * */
    public String generateHtml() {
        return driverManager.sendCommand(
                "[[\"generate_html\"]]"
        );
    }

    /**
     * generate html report
     * @param htmlName: save html use htmlName
     * @return server response string
     * */
    public String generateHtml(String htmlName) {
        return driverManager.sendCommand(
                String.format("[[\"generate_html\", {\"html_name\": \"%s\"}]]", htmlName)
        );
    }

}
