package reusablemethods;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReusableMethods
{
    public static JsonPath rawToJson(String response)
    {
        JsonPath js = new JsonPath(response);
        return js;
    }

    public static String byteToStringJsonContent(String jsonFilePath) throws IOException {
        String byteToStringJsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        return byteToStringJsonContent;
    }

    public static File uploadFile(String filePath)
    {
        File pathOfFile = new File(filePath);
        return pathOfFile;
    }

}
