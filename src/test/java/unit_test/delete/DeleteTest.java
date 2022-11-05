package unit_test.delete;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import api_testka.utils.driver_manager.DriverManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class DeleteTest {

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
    public void testDELETEAPIOnlyMethodAndURL(){
        System.out.println(driverManager.apiTest.testApiMethod("DELETE", "http://eu.httpbin.org//delete"));
    }

    @Test
    public void testDELETEAPIMethodAndURLAndCheck(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("DELETE", "http://eu.httpbin.org//delete"
                , hashMap));
    }

    @Test
    public void testDELETEAPIALL(){
        HashMap<String, ? super Object> hashMap = new HashMap<>();
        hashMap.put("status_code", 200);
        System.out.println(driverManager.apiTest.testApiMethod("DELETE", "http://eu.httpbin.org/delete"
                , false, false, false, hashMap));
    }

    @Test
    public void testDELETEAPIOnlyMethodAndURLAndSoap(){
        System.out.println(driverManager.apiTest.testApiMethod("DELETE", "http://eu.httpbin.org//delete", true));
    }

    @Test
    public void testDELETEAPIRecord(){
        System.out.println(driverManager.apiTest.testApiMethod("DELETE", "http://eu.httpbin.org//delete", false, false));
    }


}
