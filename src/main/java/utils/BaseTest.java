package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static final String BASE_URI = "https://reqres.in/api";

    @BeforeSuite
    public void setupBaseURI() {
        RestAssured.baseURI = BASE_URI;
    }
}
