package unit_test.put;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class PutTest {

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
    public void testPUTAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("PUT", "http://httpbin.org/PUT"));
    }

    @Test
    public void testPUTAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("PUT", "http://httpbin.org/PUT"
                , hashMap));
    }

    @Test
    public void testPUTAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("PUT", "http://httpbin.org/PUT"
                , false, false, false, hashMap));
    }

    @Test
    public void testPUTAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("PUT", "http://httpbin.org/PUT", true));
    }

    @Test
    public void testPUTAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("PUT", "http://httpbin.org/PUT", false, false));
    }


}
