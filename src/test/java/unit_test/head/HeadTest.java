package unit_test.head;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class HeadTest {

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
    public void testHEADAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("HEAD", "http://httpbin.org/HEAD"));
    }

    @Test
    public void testHEADAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("HEAD", "http://httpbin.org/HEAD"
                , hashMap));
    }

    @Test
    public void testHEADAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("HEAD", "http://httpbin.org/HEAD"
                , false, false, false, hashMap));
    }

    @Test
    public void testHEADAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("HEAD", "http://httpbin.org/HEAD", true));
    }

    @Test
    public void testHEADAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("HEAD", "http://httpbin.org/HEAD", false, false));
    }


}
