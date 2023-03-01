package api_testka.bind.apt_test;

import api_testka.utils.driver_manager.DriverManager;

import java.util.HashMap;
import java.util.Map;

public class APITest {

    private final DriverManager driverManager;

    /**
     *  use driverManager to init class
     * @param driverManager driver manager that manage driver
     */
    public APITest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    /**
     * @param stringBuffer: Java StringBuffer instance use to build command
     * @param resultCheckDict: include {"check_name": "check_value"}
     */
    private void makeResultDict(StringBuffer stringBuffer,HashMap<String, ? super Object> resultCheckDict){
        stringBuffer.append("\"result_check_dict\": {");
        // HashMap add k,v to here
        for (Map.Entry<String, ? super Object> set : resultCheckDict.entrySet()) {
            if (set.getValue() instanceof String)
                stringBuffer.append(String.format("\"%s\": \"%s\",", set.getKey(), set.getValue()));
            else
                stringBuffer.append(String.format("\"%s\": %s,", set.getKey(), set.getValue()));
        }
    }

    /**
     *
     * @param httpMethod: http method [GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS]
     * @param testUrl: http or soap url we want to test
     * @param soap: is this soap test?
     * @param recordRequestInfo record info true or false
     * @param cleanRecord clean all record true or false
     * @param resultCheckDict use HashMap to check response
     * @return server response
     */
    public String testApiMethod(String httpMethod, String testUrl, boolean soap, boolean recordRequestInfo,
                                boolean cleanRecord, HashMap<String, ? super Object> resultCheckDict) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        testApiMethodBuilder.append(String.format("\"soap\": %b,", soap));
        testApiMethodBuilder.append(String.format("\"record_request_info\": %b,", recordRequestInfo));
        testApiMethodBuilder.append(String.format("\"clean_record\": %b,", cleanRecord));
        makeResultDict(testApiMethodBuilder, resultCheckDict);
        testApiMethodBuilder.deleteCharAt(testApiMethodBuilder.lastIndexOf(","));
        testApiMethodBuilder.append("}").append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    /**
     * @param httpMethod: http method [GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS]
     * @param testUrl: http or soap url we want to test
     * @return server response
     */
    public String testApiMethod(String httpMethod, String testUrl) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\"", testUrl));
        testApiMethodBuilder.append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    /**
     *
     * @param httpMethod: http method [GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS]
     * @param testUrl: http or soap url we want to test
     * @param resultCheckDict use HashMap to check response
     * @return server response
     */
    public String testApiMethod(String httpMethod, String testUrl, HashMap<String, ? super Object> resultCheckDict) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        makeResultDict(testApiMethodBuilder, resultCheckDict);
        testApiMethodBuilder.deleteCharAt(testApiMethodBuilder.lastIndexOf(","));
        testApiMethodBuilder.append("}}").append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    /**
     *
     * @param httpMethod: http method [GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS]
     * @param testUrl:  http or https or soap url we want to test it
     * @param soap: is this soap test?
     * @return server response
     */
    public String testApiMethod(String httpMethod, String testUrl, boolean soap) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        testApiMethodBuilder.append(String.format("\"soap\": %b", soap));
        testApiMethodBuilder.append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    /**
     *
     * @param httpMethod: http method [GET, HEAD, POST, PUT, DELETE, PATCH, OPTIONS]
     * @param testUrl: http or soap url we want to test
     * @param recordRequestInfo record info true or false
     * @param cleanRecord clean all record true or false
     * @return server response
     */
    public String testApiMethod(String httpMethod, String testUrl, boolean recordRequestInfo,
                                boolean cleanRecord) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        testApiMethodBuilder.append(String.format("\"record_request_info\": %b,", recordRequestInfo));
        testApiMethodBuilder.append(String.format("\"clean_record\": %b", cleanRecord));
        testApiMethodBuilder.append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }


}
