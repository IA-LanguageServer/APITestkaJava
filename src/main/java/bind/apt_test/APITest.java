package bind.apt_test;

import utils.driver_manager.DriverManager;

import java.util.HashMap;
import java.util.Map;

public class APITest {

    private final DriverManager driverManager;

    public APITest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public String testApiMethod(String httpMethod, String testUrl, boolean soap, boolean recordRequestInfo,
                              boolean cleanRecord, HashMap<String, String> resultCheckDict) {
        StringBuffer testApiMethodBuilder = new StringBuffer();
        testApiMethodBuilder.append(String.format("[[\"test_api_method\", {\"http_method\": \"%s\",", httpMethod));
        testApiMethodBuilder.append(String.format("\"test_url\": \"%s\",", testUrl));
        testApiMethodBuilder.append(String.format("\"soap\": %b,", soap));
        testApiMethodBuilder.append(String.format("\"record_request_info\": %b,", recordRequestInfo));
        testApiMethodBuilder.append(String.format("\"clean_record\": %b,", cleanRecord));
        testApiMethodBuilder.append("\"result_check_dict\": {");
        // HashMap add k,v to here
        for(Map.Entry<String, String> set: resultCheckDict.entrySet()){
            testApiMethodBuilder.append(String.format("\"%s\": \"%s\",", set.getKey(), set.getValue()));
        }
        testApiMethodBuilder.deleteCharAt(testApiMethodBuilder.lastIndexOf(","));
        testApiMethodBuilder.append("}").append("}]]");
        return this.driverManager.sendCommand(testApiMethodBuilder.toString());
    }

}
