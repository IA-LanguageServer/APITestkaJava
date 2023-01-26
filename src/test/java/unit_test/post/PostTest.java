package unit_test.post;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class PostTest {

    public static DriverManager driverManager;

    @BeforeClass
    public static void setDriver() {
        driverManager = null;
        try {
            driverManager = new DriverManager(
                    "localhost",
                    9939,
                    Path.of("").toAbsolutePath() + "/generate_apitestka_driver_win.exe",
                    "windows"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void afterTest() {
        driverManager.quit();
    }

    @Test
    public void testPOSTAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("POST", "http://httpbin.org/POST"));
    }

    @Test
    public void testPOSTAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("POST", "http://httpbin.org/POST"
                , hashMap));
    }

    @Test
    public void testPOSTAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("POST", "http://httpbin.org/POST"
                , false, false, false, hashMap));
    }

    @Test
    public void testPOSTAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("POST", "http://httpbin.org/POST", true));
    }

    @Test
    public void testPOSTAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("POST", "http://httpbin.org/POST", false, false));
    }


}
