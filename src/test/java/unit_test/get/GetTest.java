package unit_test.get;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class GetTest {


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
    public void testGetAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("GET", "http://httpbin.org/get"));
    }

    @Test
    public void testGetAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("GET", "http://httpbin.org/get"
                , hashMap));
    }

    @Test
    public void testGetAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("GET", "http://httpbin.org/get"
                , false, false, false, hashMap));
    }

    @Test
    public void testGetAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("GET", "http://httpbin.org/get", true));
    }

    @Test
    public void testGetAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("GET", "http://httpbin.org/get", false, false));
    }


}
