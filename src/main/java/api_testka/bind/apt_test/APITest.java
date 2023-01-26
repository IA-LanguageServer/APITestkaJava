package api_testka.bind.apt_test;

import api_testka.utils.driver_manager.DriverManager;

import java.util.HashMap;
import java.util.Map;

public class APITest {

    private final DriverManager driverManager;

    public APITest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

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

    public String testApiMethod(String httpMethod, String testUrl) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\"", testUrl));
        testApiMethodBuilder.append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    public String testApiMethod(String httpMethod, String testUrl, HashMap<String, ? super Object> resultCheckDict) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        makeResultDict(testApiMethodBuilder, resultCheckDict);
        testApiMethodBuilder.deleteCharAt(testApiMethodBuilder.lastIndexOf(","));
        testApiMethodBuilder.append("}}").append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

    public String testApiMethod(String httpMethod, String testUrl, boolean soap) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        testApiMethodBuilder.append(String.format("\"soap\": %b", soap));
        testApiMethodBuilder.append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

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
