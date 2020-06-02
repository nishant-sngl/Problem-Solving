package Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.*;
import java.util.Properties;

public class Base {
    private static Properties prop;
    static RequestSpecification req;

    public Base() throws IOException {
        if (prop==null)
            init();
    }

    public void init() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("baseurl");

    }

    public static RequestSpecification specBuilder() throws IOException {

        if (req==null){
            PrintStream printStream = new PrintStream(new FileOutputStream(prop.getProperty("logFile")));
            req = new RequestSpecBuilder()
                    .setBaseUri(RestAssured.baseURI)
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .build();
        }
        return req;
    }

    public static void assertResponse(Response response){
        int statusCode = response.getStatusCode();
        if (statusCode>=400)
            Assert.fail("The api response status code is: " + statusCode);

        if (response.getBody().asString()==null)
            Assert.fail(("The response body is null"));

    }
}
