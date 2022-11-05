package unit_test.option;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class OptionTest {

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
    public void testOPTIONAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("OPTION", "http://httpbin.org/OPTION"));
    }

    @Test
    public void testOPTIONAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("OPTION", "http://httpbin.org/OPTION"
                , hashMap));
    }

    @Test
    public void testOPTIONAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("OPTION", "http://httpbin.org/OPTION"
                , false, false, false, hashMap));
    }

    @Test
    public void testOPTIONAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("OPTION", "http://httpbin.org/OPTION", true));
    }

    @Test
    public void testOPTIONAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("OPTION", "http://httpbin.org/OPTION", false, false));
    }


}
