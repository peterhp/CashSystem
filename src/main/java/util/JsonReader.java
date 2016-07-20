package util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Sora on 2016/7/18.
 */
public class JsonReader {

    private static Reader createFileReader(String file)
            throws FileNotFoundException {
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(
                in, Charset.forName("UTF-8"));
        return reader;
    }

    public static JsonElement getJsonFromFile(String file)
            throws FileNotFoundException {
        return new JsonParser().parse(
                createFileReader(file));
    }

    public static JsonElement getJsonFromString(String json) {
        return new JsonParser().parse(json);
    }
}
