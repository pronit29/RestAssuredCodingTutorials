package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    static String filename = "src/test/java/resources/global.properties";
    static Properties propObj = new Properties();
    static FileInputStream file;

    static {
        try {
            file = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public RequestSpecification requestSpecification() throws IOException {

        if(req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .setContentType(ContentType.JSON)
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return req;
        }
           return req;
    }

    public static String getGlobalValue(String globalKey) throws IOException {
        propObj.load(file);
        String globalProperty = propObj.getProperty(globalKey);

        return globalProperty;
    }

    public String getJsonPath(Response response, String jsonKey) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        String jsonPath = js.get(jsonKey).toString();
        return jsonPath;
    }

}
